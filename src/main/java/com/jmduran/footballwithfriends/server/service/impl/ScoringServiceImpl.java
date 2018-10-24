/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.models.MatchScore;
import com.jmduran.footballwithfriends.server.models.PlayerScore;
import com.jmduran.footballwithfriends.server.repositories.IMatchScoreRepository;
import com.jmduran.footballwithfriends.server.service.ScoringService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoringServiceImpl implements ScoringService {
    
    @Autowired
    private IMatchScoreRepository matchScoreRepository;

    @Override
    public void createPlayerScore(PlayerScore playerScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePlayerScore(String playerScoreId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePlayerScore(PlayerScore playerScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlayerScore getPlayerScore(String playerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createMatchScore(MatchScore matchScore) {
        this.matchScoreRepository.insert(matchScore);
    }

    @Override
    public void deleteMatchScore(String matchScoreId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMatchScore(MatchScore matchScore) {
        this.matchScoreRepository.save(matchScore);
    }

    @Override
    public List<MatchScore> getMatchScoresByMatch(String matchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MatchScore> getMatchScoresByPlayer(String playerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MatchScore getMatchScore(String matchId, String playerId) {
        return this.matchScoreRepository.findByMatchIdAndPlayerId(matchId, playerId);
    }

}
