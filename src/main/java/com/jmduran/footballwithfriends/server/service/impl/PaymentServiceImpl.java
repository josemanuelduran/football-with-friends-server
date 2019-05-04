/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Payment;
import com.jmduran.footballwithfriends.server.repositories.IPaymentRepository;
import com.jmduran.footballwithfriends.server.service.PaymentService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public void createPayment(Payment payment) {
        paymentRepository.insert(payment);
    }

    @Override
    public void deletePayment(String paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    
    @Override
    public List<Payment> getPlayerPayments(String playerId, Boolean paid, Integer year) {
        List<Payment> result = new ArrayList<>();
        if ((paid != null) && (year != null)) {
            result = paymentRepository.getPlayerPaymentsByYearAndStatus(playerId, year, paid);
        } else if (paid != null) {
            result = paymentRepository.getPlayerPaymentsByStatus(playerId, paid);
        } else if (year != null) {
            result = paymentRepository.getPlayerPaymentsByYear(playerId, year);
        } else {
            result = paymentRepository.findByPlayerId(playerId);
        }
        return result;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId).get();
    }

    @Override
    public void createMatchPayments(Match match) {
        match.getCallUp().stream().filter(player -> !player.getPlayer().getFixed()).forEach(player -> {
            Payment newPayment = 
                Payment.builder()
                    .playerId(player.getPlayer().getId())
                    .matchId(match.getId())
                    .amount(4f)
                    .matchDate(match.getDate())
                    .paid(false)
                    .year(LocalDate.now().getYear())
                    .build();
            this.createPayment(newPayment);
        });
    }
}
