/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service;

import com.jmduran.footballwithfriends.server.models.Payment;
import java.util.List;

public interface PaymentService {
    
    void createPayment(Payment payment);
    void deletePayment(String paymentId);
    void updatePayment(Payment payment);    
    List<Payment> getPayments();
    Payment getPayment(String paymentId);   
    
}
