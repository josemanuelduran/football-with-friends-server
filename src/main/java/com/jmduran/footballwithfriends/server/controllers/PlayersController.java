/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.service.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fwf/player")
public class PlayersController {
    
    @Autowired
    private PlayerService service;
    
    @RequestMapping(value = "", 
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayer(@RequestBody Player player){        
        service.createPlayer(player);
    }
    
    @RequestMapping(value = "/{playerId}", 
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePlayer(@PathVariable String playerId){        
        service.deletePlayer(playerId);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePlayer(@RequestBody Player player){        
        service.updatePlayer(player);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> getPlayers(){        
        return service.getPlayers();
    }
    
    @RequestMapping(value = "/{playerId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayer(@PathVariable String playerId){        
        Player player = service.getPlayer(playerId);
        ResponseEntity<Player> response;
        if (player == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(player, HttpStatus.OK);            
        }        
        return response;
    }        
}
