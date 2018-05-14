/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.service.MatchService;
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
@RequestMapping("/fwf/match")
public class MatchesController {

    @Autowired
    private MatchService service;
    
    @RequestMapping(value = "", 
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createMatch(@RequestBody Match match){        
        service.createMatch(match);
    }
    
    @RequestMapping(value = "/{matchId}", 
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatch(@PathVariable String matchId){        
        service.deleteMatch(matchId);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateMatch(@RequestBody Match match){        
        service.updateMatch(match);
    }
    
    @RequestMapping(value = "/{matchId}/player", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void joinPlayerCallUp(@PathVariable String matchId, @RequestBody Player player){        
        service.joinPlayerCallUp(matchId, player);
    }
    
    @RequestMapping(value = "/{matchId}/player/{playerId}", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void unJoinPlayerCallUp(@PathVariable String matchId, @PathVariable String playerId){        
        service.unJoinPlayerCallUp(matchId, playerId);
    }
    
    @RequestMapping(value = "/{matchId}/teams", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTeams(@PathVariable String matchId, @RequestBody List<Match.Team> teams ){        
        service.updateTeams(matchId, teams);
    }
    
    @RequestMapping(value = "/{matchId}/discards", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void joinPlayerDiscard(@PathVariable String matchId, @RequestBody Match.PlayerDiscard player){        
        service.joinPlayerDiscards(matchId, player);
    }
    
    @RequestMapping(value = "/{matchId}/discards/player/{playerId}", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void unjoinPlayerDiscard(@PathVariable String matchId, @PathVariable String playerId){        
        service.unjoinPlayerDiscards(matchId, playerId);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Match> getMatches(){        
        return service.getMatchs();
    }
    
    @RequestMapping(value = "/{matchId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Match> getMatch(@PathVariable String matchId){        
        Match match = service.getMatch(matchId);
        ResponseEntity<Match> response;
        if (match == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(match, HttpStatus.OK);            
        }        
        return response;
    }        
}
