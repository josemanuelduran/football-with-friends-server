/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.repositories;

import com.jmduran.footballwithfriends.server.models.MatchScore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMatchScoreRepository extends MongoRepository<MatchScore, String>{

    MatchScore findByMatchIdAndPlayerId(String matchId, String playerId);
    
}
