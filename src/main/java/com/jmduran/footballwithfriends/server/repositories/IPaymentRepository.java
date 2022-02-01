/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.repositories;

import com.jmduran.footballwithfriends.server.models.Payment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.Month;

public interface IPaymentRepository extends MongoRepository<Payment, String> {
    
    List<Payment> findByPlayerId(String playerId);
    @Query("{ 'playerId' : ?0, 'year' : ?1, 'paid' : ?2 }")
    List<Payment> getPlayerPaymentsByYearAndStatus(String playerId, Integer year, Boolean paid);
    @Query("{ 'playerId' : ?0, 'year' : ?1 }")
    List<Payment> getPlayerPaymentsByYear(String playerId, Integer year);
    @Query("{ 'playerId' : ?0, 'paid' : ?1 }")
    List<Payment> getPlayerPaymentsByStatus(String playerId, Boolean paid);  
    @Query("{ 'playerId' : ?0, 'year' : ?1, 'month' : ?2 }")
    Payment getPlayerPaymentByYearAndMonth(String playerId, Integer year, Month month);
}
