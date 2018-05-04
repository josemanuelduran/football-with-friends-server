/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmduran.footballwithfriends.server.enums.Role;
import com.jmduran.footballwithfriends.server.models.UserFWF;
import com.jmduran.footballwithfriends.server.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
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
//@WebMvcTest(value = UsersController.class, secure = false)
public class UsersControllerTest {
    
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @MockBean
//    private UserService mockService;
//    
//    private UserFWF mockUser1;
//    private UserFWF mockUser2;
//    private List<UserFWF> mockListUsers;
//    
//    public UsersControllerTest() {
//    }
//    
//    @Before
//    public void setUp() {
//        
//        List<Role> rolesList = new ArrayList<>();
//        rolesList.add(Role.ADMIN);
//        mockUser1 = UserFWF.builder()
//                .id("1")
//                .active(Boolean.TRUE)
//                .fullName("full name")
//                .password("password")
//                .playerId("1")
//                .roles(rolesList)
//                .username("username")
//                .build();
//        
//        mockUser2 = UserFWF.builder()
//                .id("2")
//                .fullName("full name2")
//                .password("password2")
//                .playerId("2")
//                .roles(rolesList)
//                .username("username2")
//                .build();
//        
//        mockUser2.setActive(Boolean.TRUE);
//        mockListUsers = new ArrayList<>();
//        mockListUsers.add(mockUser1);
//        mockListUsers.add(mockUser2);
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of createUser method, of class UsersController.
//     */
//    @Test
//    public void testCreateUser() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .post("/fwf/user")
//                        .content(new ObjectMapper().writeValueAsString(mockUser1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isCreated());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the user to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }         
//    }
//
//    /**
//     * Test of deleteUser method, of class UsersController.
//     */
//    @Test
//    public void testDeleteUser() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .delete("/fwf/user/1");
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }    
//    }
//
//    /**
//     * Test of updateUser method, of class UsersController.
//     */
//    @Test
//    public void testUpdateUser() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .put("/fwf/user")
//                        .content(new ObjectMapper().writeValueAsString(mockUser1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the user to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }     
//    }
//
//    /**
//     * Test of getUsers method, of class UsersController.
//     */
//    @Test
//    public void testGetUsers() {
//        when(mockService.getUsers()).thenReturn(mockListUsers);
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/user")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockListUsers);
//
//            assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the user to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
//
//    /**
//     * Test of getUser method, of class UsersController.
//     */
//    @Test
//    public void testGetUserShouldReturnTheUserGivenIfTheUserExist() {
//        when(mockService.getUser("1")).thenReturn(mockUser1);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/user/1")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockUser1);
//
//            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the user to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }    
//    
//    /**
//     * Test of getUser method, of class UsersController.
//     */
//    @Test
//    public void testGetUserShouldReturnStatusNotFoundIfTheUserNotExist() {
//        when(mockService.getUser("3")).thenReturn(null);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/user/3")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
    
}
