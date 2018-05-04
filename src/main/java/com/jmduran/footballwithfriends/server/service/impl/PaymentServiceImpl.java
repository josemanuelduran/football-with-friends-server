/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.models.Payment;
import com.jmduran.footballwithfriends.server.repositories.IPaymentRepository;
import com.jmduran.footballwithfriends.server.service.PaymentService;
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
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId).get();
    }
}
