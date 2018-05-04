/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmduran.footballwithfriends.server.enums.TeamColor;
import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.service.MatchService;
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
//@WebMvcTest(value = MatchesController.class, secure = false)
public class MatchesControllerTest {
    
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @MockBean
//    private MatchService mockService;
//    
//    private Match mockMatch1;
//    private Match mockMatch2;
//    private List<Match> mockListMatches;
//            
//    public MatchesControllerTest() {
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
//        mockMatch1 = Match.builder()
//            .id("1")
//            .date(new Date())
//            .mvp("1")
//            .numPlayers(14)
//            .openCallUp(Boolean.TRUE)
//            .build();
//        
//        mockMatch2 = Match.builder()
//            .id("2")
//            .date(new Date())
//            .mvp("2")
//            .numPlayers(14)
//            .openCallUp(Boolean.TRUE)
//            .build();
//        
//        Match.PlayerCallUp mockPlayer = new Match.PlayerCallUp();
//        mockPlayer.setPlayer(new Match.SimplyPlayer("name", "1", true));
//        mockPlayer.setDateCallUp(new Date());
//        List<Match.PlayerCallUp> callUp = new ArrayList<>();
//        callUp.add(mockPlayer);
//        mockMatch1.setCallUp(callUp);
//        
//        Match.Team mockTeam = new Match.Team();
//        mockTeam.setColor(TeamColor.BLACK);
//        mockTeam.setGoals(0);
//        List<Match.SimplyPlayer> playersTeam = new ArrayList<>();
//        playersTeam.add(new Match.SimplyPlayer("name", "1", true));
//        mockTeam.setPlayers(playersTeam);
//        mockMatch1.setTeam1(mockTeam);
//        mockMatch1.setTeam2(mockTeam);
//        
//        mockListMatches = new ArrayList<>();
//        mockListMatches.add(mockMatch1);
//        mockListMatches.add(mockMatch2);
//        
//    }
//    
//    @After
//    public void tearDown() {
//    }

    /**
     * Test of createMatch method, of class MatchesController.
     */
//    @Test
//    public void testCreateMatch() {        
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .post("/fwf/match")
//                        .content(new ObjectMapper().writeValueAsString(mockMatch1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isCreated());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the match to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }               
//    }

    /**
     * Test of deleteMatch method, of class MatchesController.
     */
//    @Test
//    public void testDeleteMatch() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .delete("/fwf/match/1");
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }      
//    }

    /**
     * Test of updateMatch method, of class MatchesController.
     */
//    @Test
//    public void testUpdateMatch() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .put("/fwf/match")
//                        .content(new ObjectMapper().writeValueAsString(mockMatch1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the match to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }     
//    }

    /**
     * Test of getMatches method, of class MatchesController.
     */
//    @Test
//    public void testGetMatches() {
//        when(mockService.getMatchs()).thenReturn(mockListMatches);
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/match")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockListMatches);
//
//            assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the match to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }

    /**
     * Test of getMatch method, of class MatchesController.
     */
//    @Test
//    public void testGetMatchShouldReturnTheMatchGivenIfTheMatchExist() {
//        when(mockService.getMatch("1")).thenReturn(mockMatch1);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/match/1")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockMatch1);
//
//            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the match to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }    
    
    /**
     * Test of getMatch method, of class MatchesController.
     */
//    @Test
//    public void testGetMatchShouldReturnStatusNotFoundIfTheMatchNotExist() {
//        when(mockService.getMatch("3")).thenReturn(null);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/match/3")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
}
