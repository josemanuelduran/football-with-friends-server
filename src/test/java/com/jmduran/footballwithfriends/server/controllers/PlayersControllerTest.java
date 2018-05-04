/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmduran.footballwithfriends.server.enums.Position;
import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.service.PlayerService;
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
//@WebMvcTest(value = PlayersController.class, secure = false)
public class PlayersControllerTest {
    
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @MockBean
//    private PlayerService mockService;
//    
//    private Player mockPlayer1;
//    private Player mockPlayer2;
//    private List<Player> mockListPlayers;
//    
//    public PlayersControllerTest() {
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
//        mockPlayer1 = Player.builder()
//                .id("1")
//                .active(true)
//                .alias("alias")
//                .currentScore(10.0)
//                .fixed(Boolean.TRUE)
//                .fixedDate(new Date())
//                .fullName("full name")
//                .mainPosition(Position.SWEEPER)
//                .registerDate(new Date())
//                .secondaryPosition(Position.FORWARD)
//                .tertiaryPosition(Position.LEFT_BACK)
//                .userId("1")
//                .build();
//        
//        mockPlayer2 = Player.builder()
//                .id("2")
//                .alias("alias2")
//                .currentScore(9.0)
//                .fixed(Boolean.TRUE)
//                .fixedDate(new Date())
//                .fullName("full name2")
//                .mainPosition(Position.SWEEPER)
//                .registerDate(new Date())
//                .secondaryPosition(Position.FORWARD)
//                .tertiaryPosition(Position.LEFT_BACK)
//                .userId("2")
//                .build();
//        
//        mockPlayer2.setActive(Boolean.TRUE);
//        mockListPlayers = new ArrayList<>();
//        mockListPlayers.add(mockPlayer1);
//        mockListPlayers.add(mockPlayer2);
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of createPlayer method, of class PlayersController.
//     */
//    @Test
//    public void testCreatePlayer() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .post("/fwf/player")
//                        .content(new ObjectMapper().writeValueAsString(mockPlayer1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isCreated());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the player to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }         
//    }
//
//    /**
//     * Test of deletePlayer method, of class PlayersController.
//     */
//    @Test
//    public void testDeletePlayer() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .delete("/fwf/player/1");
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }    
//    }
//
//    /**
//     * Test of updatePlayer method, of class PlayersController.
//     */
//    @Test
//    public void testUpdatePlayer() {
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                        .put("/fwf/player")
//                        .content(new ObjectMapper().writeValueAsString(mockPlayer1))
//                        .contentType(MediaType.APPLICATION_JSON);
//            mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the player to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        }     
//    }
//
//    /**
//     * Test of getPlayers method, of class PlayersController.
//     */
//    @Test
//    public void testGetPlayers() {
//        when(mockService.getPlayers()).thenReturn(mockListPlayers);
//        try{
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/player")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockListPlayers);
//
//            assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the player to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
//
//    /**
//     * Test of getPlayer method, of class PlayersController.
//     */
//    @Test
//    public void testGetPlayerShouldReturnThePlayerGivenIfThePlayerExist() {
//        when(mockService.getPlayer("1")).thenReturn(mockPlayer1);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/player/1")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//            String expected = new ObjectMapper().writeValueAsString(mockPlayer1);
//
//            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        } catch (JsonProcessingException jpe) {
//            fail("Error parsing the player to json.");
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }    
//    
//    /**
//     * Test of getPlayer method, of class PlayersController.
//     */
//    @Test
//    public void testGetPlayerShouldReturnStatusNotFoundIfThePlayerNotExist() {
//        when(mockService.getPlayer("3")).thenReturn(null);
//        try {
//            RequestBuilder requestBuilder = 
//                    MockMvcRequestBuilders
//                            .get("/fwf/player/3")
//                            .accept(MediaType.APPLICATION_JSON);
//
//            mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
//        } catch (Exception exp) {
//            fail("Error performing the request.");
//        } 
//    }
    
}
