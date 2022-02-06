/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.jmduran.footballwithfriends.server.models.PlayerStats;
import com.jmduran.footballwithfriends.server.service.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fwf/playerStats")
public class PlayerStatsController {

    @Autowired
    private PlayerStatsService service;

    @RequestMapping(value = "", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerStats> getPlayerStats(@RequestParam(value = "playerId", required = false) String playerId){        
        PlayerStats playerStats = service.getPlayerStats(playerId);
        ResponseEntity<PlayerStats> response;
        if (playerStats == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(playerStats, HttpStatus.OK);            
        }        
        return response;
    }        
}
