/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service;

import com.jmduran.footballwithfriends.server.models.MatchScore;
import com.jmduran.footballwithfriends.server.models.PlayerScore;
import java.util.List;

public interface ScoringService {
    
    void createPlayerScore(PlayerScore playerScore);
    void deletePlayerScore(String playerScoreId);
    void updatePlayerScore(PlayerScore playerScore);    
    PlayerScore getPlayerScore(String playerId);
    void createMatchScore(MatchScore matchScore);
    void deleteMatchScore(String matchScoreId);
    void updateMatchScore(MatchScore matchScore);    
    List<MatchScore> getMatchScoresByMatch(String matchId);
    List<MatchScore> getMatchScoresByPlayer(String playerId);
    MatchScore getMatchScore(String matchId, String playerId);

}
