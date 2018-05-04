/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Match.PlayerCallUp;
import com.jmduran.footballwithfriends.server.models.Match.SimplyPlayer;
import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.repositories.IMatchRepository;
import com.jmduran.footballwithfriends.server.service.MatchService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private IMatchRepository matchRepository;

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
        matchRepository.save(match);
    }

    @Override
    public List<Match> getMatchs() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatch(String matchId) {
        return matchRepository.findById(matchId).get();
    }

    @Override
    synchronized public void joinPlayerCallUp(String matchId, Player player) {
        Match match = matchRepository.findById(matchId).get();
        if (match.getCallUp() == null) {
            List<PlayerCallUp> callUp = new ArrayList<>();     
            match.setCallUp(callUp);
        }        
        PlayerCallUp playerCallUp = new PlayerCallUp();
        playerCallUp.setDateCallUp(new Date());
        SimplyPlayer simplyPlayer = new SimplyPlayer(player.getAlias(), player.getId(), player.getFixed());
        playerCallUp.setPlayer(simplyPlayer);
        match.getCallUp().add(playerCallUp);
        matchRepository.save(match);
    }
    
    @Override
    synchronized public void unJoinPlayerCallUp(String matchId, String playerId) {
        Match match = matchRepository.findById(matchId).get();
        match.getCallUp().removeIf(item -> item.getPlayer().getId().equals(playerId));
        
        matchRepository.save(match);
    }

    @Override
    synchronized public void updateTeams(String matchId, List<Match.Team> teams) {
        Match match = matchRepository.findById(matchId).get();
        match.setTeam1(teams.get(0));
        match.setTeam2(teams.get(1));
        
        matchRepository.save(match);
    }
}