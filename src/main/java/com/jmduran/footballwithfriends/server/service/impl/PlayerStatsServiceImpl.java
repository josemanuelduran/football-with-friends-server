/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.enums.TeamColor;
import com.jmduran.footballwithfriends.server.models.PlayerStats;
import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.repositories.IMatchScoreRepository;
import com.jmduran.footballwithfriends.server.repositories.IMatchRepository;
import com.jmduran.footballwithfriends.server.service.PlayerStatsService;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatsServiceImpl implements PlayerStatsService {

    @Autowired
    private IMatchScoreRepository matchScoreRepository;
    
    @Autowired
    private IMatchRepository matchRepository;

    private static enum MatchResult {
        WON, LOST, TIED
    }
    
    @Override
    public PlayerStats getPlayerStats(String playerId) {
        List<Match> matchesPlayed = matchRepository.getMatchesPlayedByPlayer(new ObjectId(playerId), false);
        matchesPlayed = matchesPlayed.stream().filter(match -> match.getTeam1() != null && match.getTeam2() != null).collect(Collectors.toList());
        List<Match> matchesWonAsWhite = matchesPlayed.stream().filter(match -> 
                this.isPlayedAsWhite(match, playerId) && this.getMatchResult(match, playerId).equals(MatchResult.WON)
        ).collect(Collectors.toList());
        List<Match> matchesWonAsBlack = matchesPlayed.stream().filter(match -> 
                !this.isPlayedAsWhite(match, playerId) && this.getMatchResult(match, playerId).equals(MatchResult.WON)
        ).collect(Collectors.toList());
        List<Match> matchesLostAsWhite = matchesPlayed.stream().filter(match -> 
                this.isPlayedAsWhite(match, playerId) && this.getMatchResult(match, playerId).equals(MatchResult.LOST)
        ).collect(Collectors.toList());
        List<Match> matchesLostAsBlack = matchesPlayed.stream().filter(match -> 
                !this.isPlayedAsWhite(match, playerId) && this.getMatchResult(match, playerId).equals(MatchResult.LOST)
        ).collect(Collectors.toList());
        List<Match> matchesTiedAsWhite = matchesPlayed.stream().filter(match -> 
                this.isPlayedAsWhite(match, playerId) && this.getMatchResult(match, playerId).equals(MatchResult.TIED)
        ).collect(Collectors.toList());
        List<Match> matchesTiedAsBlack = matchesPlayed.stream().filter(match -> 
                !this.isPlayedAsWhite(match, playerId) && this.getMatchResult(match, playerId).equals(MatchResult.TIED)
        ).collect(Collectors.toList());
        PlayerStats playerStats =
                PlayerStats.builder()
                    .matchesWonAsBlack(matchesWonAsBlack.size())
                    .matchesWonAsWhite(matchesWonAsWhite.size())
                    .matchesLostAsBlack(matchesLostAsBlack.size())
                    .matchesLostAsWhite(matchesLostAsWhite.size())
                    .matchesTiedAsBlack(matchesTiedAsBlack.size())
                    .matchesTiedAsWhite(matchesTiedAsWhite.size())
                    .matchesPlayedAsWhite(matchesWonAsWhite.size() + matchesLostAsWhite.size() + matchesTiedAsWhite.size())
                    .matchesPlayedAsBlack(matchesWonAsBlack.size() + matchesLostAsBlack.size() + matchesTiedAsBlack.size())  
                    .goalsScored(this.getGoalsScored(matchesPlayed, playerId))
                    .goalsConceded(this.getGoalsConceded(matchesPlayed, playerId))
                    .build();
        
             
        return playerStats;
    }
    
    private Boolean isPlayedAsWhite(Match match, String playerId) {
        boolean belongsTeam1 = match.getTeam1().getPlayers().stream().anyMatch(el -> el.getId().equals(playerId));
        return (belongsTeam1 && match.getTeam1().getColor().equals(TeamColor.WHITE)) || (!belongsTeam1 && match.getTeam1().getColor().equals(TeamColor.BLACK));
    }
    
    private MatchResult getMatchResult(Match match, String playerId) {
        if (match.getTeam1().getGoals().equals(match.getTeam2().getGoals())) {
            return MatchResult.TIED;
        } else {
            Boolean team1Won = match.getTeam1().getGoals() > match.getTeam2().getGoals();
            boolean belongsTeam1 = match.getTeam1().getPlayers().stream().anyMatch(el -> el.getId().equals(playerId));
            if ((belongsTeam1 && team1Won) || (!belongsTeam1 && !team1Won)) {
                return MatchResult.WON;
            } else {
                return MatchResult.LOST;
            }
        }        
    }
    
    private Integer getGoalsScored(List<Match> matches, String playerId) {
        int goalsScored = matches.stream().mapToInt(match -> {
            boolean belongsTeam1 = match.getTeam1().getPlayers().stream().anyMatch(el -> el.getId().equals(playerId));
            return belongsTeam1 ? match.getTeam1().getGoals() : match.getTeam2().getGoals();
        }).reduce(0, (acc, curr) -> acc + curr);
        return goalsScored;
    }    
    
    private Integer getGoalsConceded(List<Match> matches, String playerId) {
        int goalsConceded = matches.stream().mapToInt(match -> {
            boolean belongsTeam1 = match.getTeam1().getPlayers().stream().anyMatch(el -> el.getId().equals(playerId));
            return belongsTeam1 ? match.getTeam2().getGoals() : match.getTeam1().getGoals();
        }).reduce(0, (acc, curr) -> acc + curr);
        return goalsConceded;
    }  
}
