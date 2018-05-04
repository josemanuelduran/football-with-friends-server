/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.repositories;

import com.jmduran.footballwithfriends.server.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMatchRepository extends MongoRepository<Match, String> {
    
}
