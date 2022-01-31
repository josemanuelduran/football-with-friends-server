/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.jmduran.footballwithfriends.server.models.Payment;
import com.jmduran.footballwithfriends.server.service.PaymentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fwf/payment")
public class PaymentsController {
    
    @Autowired
    private PaymentService service;
    
    @RequestMapping(value = "", 
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayment(@RequestBody Payment payment){        
        service.createPayment(payment);
    }
    
    @RequestMapping(value = "/{paymentId}", 
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePayment(@PathVariable String paymentId){        
        service.deletePayment(paymentId);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePayment(@RequestBody Payment payment){        
        service.updatePayment(payment);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Payment> getPayments(
            @RequestParam(value = "playerId", required = false) String playerId,
            @RequestParam(value = "paid", required = false) Boolean paid,
            @RequestParam(value = "year", required = false) Integer year
    ){  
        if (playerId == null) {
            List<Payment> result = service.getPayments();      
            if (paid != null) {
                result = result.stream().filter(el -> el.getPaid() == paid).collect(Collectors.toList());
            }
            if (year != null) {
                result = result.stream().filter(el -> el.getYear() == year).collect(Collectors.toList());
            }           
            return result;
        } else {
            return service.getPlayerPayments(playerId, paid, year);
        }
        
    }
    
    @RequestMapping(value = "/{paymentId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPayment(@PathVariable String paymentId){        
        Payment payment = service.getPayment(paymentId);
        ResponseEntity<Payment> response;
        if (payment == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(payment, HttpStatus.OK);            
        }        
        return response;
    }        
}
