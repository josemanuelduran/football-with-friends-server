/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service;

import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Payment;
import java.util.List;

public interface PaymentService {
    
    void createPayment(Payment payment);
    void deletePayment(String paymentId);
    void updatePayment(Payment payment);    
    List<Payment> getPayments();
    List<Payment> getPlayerPayments(String playerId, Boolean paid, Integer year);
    Payment getPayment(String paymentId);   
    void createMatchPayments(Match match);
    
}
