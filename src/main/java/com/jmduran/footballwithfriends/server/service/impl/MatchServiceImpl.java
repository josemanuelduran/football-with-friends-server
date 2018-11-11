/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.mail.FWFMailSenderService;
import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Match.PlayerCallUp;
import com.jmduran.footballwithfriends.server.models.Match.PlayerDiscard;
import com.jmduran.footballwithfriends.server.models.Match.SimplyPlayer;
import com.jmduran.footballwithfriends.server.models.MatchScore;
import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.models.PlayerScore;
import com.jmduran.footballwithfriends.server.repositories.IMatchRepository;
import com.jmduran.footballwithfriends.server.repositories.IMatchScoreRepository;
import com.jmduran.footballwithfriends.server.repositories.IPlayerRepository;
import com.jmduran.footballwithfriends.server.service.MatchService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    private static final String ASUNTO = "Fútbol con Amigos: Notificación"; 
    
    @Autowired
    private IMatchRepository matchRepository;
    
    @Autowired
    private IPlayerRepository playerRepository;
    
    @Autowired
    private IMatchScoreRepository matchScoreRepository;
    
    @Autowired
    private FWFMailSenderService mailSender;

    @Override
    public void createMatch(Match match) {
        matchRepository.insert(match);
    }

    @Override
    public void deleteMatch(String matchId) {
        matchRepository.deleteById(matchId);
    }

    @Override
    public void updateMatch(Match match) {
        Match oldMatch = matchRepository.findById(match.getId()).get();
        matchRepository.save(match);
        if (!oldMatch.getCancelled() && match.getCancelled()) {
            mailSender.sendMailToAll(ASUNTO, 
                    "Vaya, que mala suerte, el partido " + match.getName() +
                    " ha sido cancelado");
        } else if (!oldMatch.getOpenCallUp()&& match.getOpenCallUp()) {
            mailSender.sendMailToAll(ASUNTO, 
                    "La convocatoria del partido " + match.getName() +
                    " se ha abierto.<br>Apúntate rápido!!!");
        }
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatch(String matchId) {
        return matchRepository.findById(matchId).get();
    }

    @Override
    synchronized public void joinPlayerCallUp(String matchId, Player player) {
        Match match = matchRepository.findById(matchId).get();
        Boolean joinAsReserve = false;
        String idPlayerReserved = null;
        if (match.getCallUp() == null) {     
            match.setCallUp(new ArrayList<>());
        }      
        if (match.getCallUp().size() == match.getNumPlayers()) {
            if (!player.getFixed()) {
                joinAsReserve = true;
            } else {
                List<PlayerCallUp> listNoFixed =
                        match.getCallUp().stream()
                                .filter(el -> !el.getPlayer().getFixed())
                                .sorted(Comparator.comparing(PlayerCallUp::getDateCallUp).reversed())
                                .collect(Collectors.toList());
                if (listNoFixed.size() > 0) {
                    if (match.getReserves() == null) {
                        match.setReserves(new ArrayList<>());
                    }
                    match.getReserves().add(listNoFixed.get(0));                    
                    match.getCallUp().removeIf(item -> item.getPlayer().getId().equals(listNoFixed.get(0).getPlayer().getId()));
                    idPlayerReserved = listNoFixed.get(0).getPlayer().getId();
                } else {
                    throw new RuntimeException("Parece que hay mas fijos que jugadores en el partido");
                }
            }
        }
        PlayerCallUp playerCallUp = new PlayerCallUp();
        playerCallUp.setDateCallUp(new Date());
        SimplyPlayer simplyPlayer = new SimplyPlayer(player.getAlias(), player.getId(), player.getFixed());
        playerCallUp.setPlayer(simplyPlayer);
        if (joinAsReserve) {
            if (match.getReserves() == null) {
                match.setReserves(new ArrayList<>());
            }
            match.getReserves().add(playerCallUp);
        } else {
            match.getCallUp().add(playerCallUp);
        }
        matchRepository.save(match);
        if (player.getEmail() != null && !player.getEmail().equals("")) {
            if (joinAsReserve) {
                mailSender.sendMail(player.getEmail(), ASUNTO, 
                        "Lo siento " + player.getAlias()  + ", pero de momento eres reserva para el partido " + match.getName());
            } else {
                mailSender.sendMail(player.getEmail(), ASUNTO, 
                        "Enhorabuena " + player.getAlias()  + ", estás convocado para el partido " + match.getName());
            }
        }
        if (idPlayerReserved != null) {
            Player playerReserved = playerRepository.findById(idPlayerReserved).get();
            if (playerReserved.getEmail() != null && !playerReserved.getEmail().equals("")) {
                mailSender.sendMail(playerReserved.getEmail(), ASUNTO, 
                        "Lo siento " + playerReserved.getAlias()  + ", pasas a estar en la reserva para el partido " + match.getName() +
                        " <br>" + player.getAlias() + " ha entrado en tu lugar");
            }
        }
    }
    
    @Override
    synchronized public void unJoinPlayerCallUp(String matchId, String playerId) {
        Match match = matchRepository.findById(matchId).get();
        String idRecoveredPlayer = null;
        Boolean playerInCallUp = match.getCallUp().removeIf(item -> item.getPlayer().getId().equals(playerId));
        if (playerInCallUp && match.getReserves() != null && match.getReserves().size() > 0) {
            List<PlayerCallUp> listReserves =
                        match.getReserves().stream()
                                .sorted(Comparator.comparing(PlayerCallUp::getDateCallUp))
                                .collect(Collectors.toList());
            match.getCallUp().add(listReserves.get(0));
            idRecoveredPlayer = listReserves.get(0).getPlayer().getId();
            match.getReserves().removeIf(item -> item.getPlayer().getId().equals(listReserves.get(0).getPlayer().getId()));
        } else if (match.getReserves() != null && match.getReserves().size() > 0){
            match.getReserves().removeIf(item -> item.getPlayer().getId().equals(playerId));
        }        
        matchRepository.save(match);
        if (idRecoveredPlayer != null) {
            Player playerRecovered = playerRepository.findById(idRecoveredPlayer).get();
            if (playerRecovered.getEmail() != null && !playerRecovered.getEmail().equals("")) {
                mailSender.sendMail(playerRecovered.getEmail(), ASUNTO, 
                        "Enhorabuena " + playerRecovered.getAlias()  + ", acabas de entrar en la convocatoria para el partido " + match.getName());
            }
        }
    }

    @Override
    synchronized public void updateTeams(String matchId, List<Match.Team> teams) {
        Match match = matchRepository.findById(matchId).get();
        match.setTeam1(teams.get(0));
        match.setTeam2(teams.get(1));
        
        matchRepository.save(match);
        mailSender.sendMailToAll(ASUNTO, 
                "Ya están los equipos para el partido " + match.getName() +
                ".<br>¡¡¡Vaya equipito te ha tocado!!! <br>Entra en la aplicación y compruébalo.");
    }
    
    @Override
    synchronized public void joinPlayerDiscards(String matchId, PlayerDiscard player) {
        Match match = matchRepository.findById(matchId).get();
        if (match.getDiscards() == null) {
            List<PlayerDiscard> discards = new ArrayList<>();     
            match.setDiscards(discards);
        }        
        match.getDiscards().add(player);
        matchRepository.save(match);
    }
    
    @Override
    synchronized public void unjoinPlayerDiscards(String matchId, String playerId) {
        Match match = matchRepository.findById(matchId).get();
        if (match.getDiscards() != null) {
            match.getDiscards().removeIf(item -> item.getPlayer().getId().equals(playerId));            
            matchRepository.save(match);
        }        
    }

    @Override
    public List<PlayerScore> getPlayerScores(String matchId) {
        List<MatchScore> matchScores = matchScoreRepository.findByMatchId(matchId);
        List<PlayerScore> playerScores = new ArrayList<>();
        matchScores.stream().forEach(matchScore -> {
            matchScore.getScores().stream().forEach(score -> {
                boolean playerIn = false;
                for (PlayerScore playerScore : playerScores) {
                    if (playerScore.getPlayer().getId().equals(score.getIdPlayer())) {
                        playerIn = true;
                        if (score.getScore() != null) {
                            Integer currentTotalScore = playerScore.getTotalScore();
                            if (currentTotalScore == null) {
                                currentTotalScore = 0;
                            } 
                            playerScore.setTotalScore(currentTotalScore + score.getScore());
                            playerScore.setNumVotes(playerScore.getNumVotes() + 1);
                        }
                    }
                }
                if (!playerIn) {
                    playerScores.add(
                        new PlayerScore(
                            new PlayerScore.SimplyPlayer(score.getNamePlayer(), score.getIdPlayer()),
                            score.getScore(),
                            score.getScore() != null ? 1 : 0
                        )
                    );
                }                 
            });
        });
        return playerScores;
    }
}
