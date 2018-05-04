/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmduran.footballwithfriends.server.models.Payment;
import com.jmduran.footballwithfriends.server.service.PaymentService;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.skyscreamer.jsonassert.JSONAssert;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = PaymentsController.class, secure = false)
public class PaymentsControllerTest {
    
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @MockBean
//    private PaymentService mockService;
//    
//    private Payment mockPayment1;
//    private Payment mockPayment2;
//    private List<Payment> mockListPayments;
//    
//    public PaymentsControllerTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        
//        mockPayment1 = Payment.builder()
//                .id("1")
//                .month(Month.MARCH)
//                .paymentDate(new Date())
//                .playerId("1")
//                .build();
//        
//        mockPayment2 = Payment.builder()
//                .id("2")
//                .matchId("2")
//                .paymentDate(new Date())
//                .playerId("2")
//                .build();
//        
//        mockPayment1.setYear(2018);
//        mockListPayments = new ArrayList<>();
//        mockListPayments.add(mockPayment1);
//        mockListPayments.add(mockPayment2);
//    }
//    
//    @After
//    public void tearDown() {
//    }

//    /**
//     * Test of createPayment method, of class PaymentsController.
//     */
//    @Test
//    public void testCreatePayment() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .post("/fwf/payment")
//                        .content(new ObjectMapper().writeValueAsString(mockPayment1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isCreated());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the payment to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }         
//    }
//
//    /**
//     * Test of deletePayment method, of class PaymentsController.
//     */
//    @Test
//    public void testDeletePayment() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .delete("/fwf/payment/1");
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }    
//    }
//
//    /**
//     * Test of updatePayment method, of class PaymentsController.
//     */
//    @Test
//    public void testUpdatePayment() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .put("/fwf/payment")
//                        .content(new ObjectMapper().writeValueAsString(mockPayment1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the payment to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }     
//    }
//
//    /**
//     * Test of getPayments method, of class PaymentsController.
//     */
//    @Test
//    public void testGetPayments() {
//        when(mockService.getPayments()).thenReturn(mockListPayments);
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/payment")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockListPayments);
//
//            assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the payment to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
//
//    /**
//     * Test of getPayment method, of class PaymentsController.
//     */
//    @Test
//    public void testGetPaymentShouldReturnThePaymentGivenIfThePaymentExist() {
//        when(mockService.getPayment("1")).thenReturn(mockPayment1);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/payment/1")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockPayment1);
//
//            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the payment to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }    
//    
//    /**
//     * Test of getPayment method, of class PaymentsController.
//     */
//    @Test
//    public void testGetPaymentShouldReturnStatusNotFoundIfThePaymentNotExist() {
//        when(mockService.getPayment("3")).thenReturn(null);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/payment/3")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
    
}
