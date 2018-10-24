/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.jmduran.footballwithfriends.server.models.MatchScore;
import com.jmduran.footballwithfriends.server.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fwf/matchScoring")
public class MatchScoringController {

    @Autowired
    private ScoringService service;
            
    @RequestMapping(value = "", 
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createScoring(@RequestBody MatchScore matchScore){        
        service.createMatchScore(matchScore);
    }
    
    @RequestMapping(value = "/{matchScoreId}", 
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatchScore(@PathVariable String matchScoreId){        
        service.deleteMatchScore(matchScoreId);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateMatchScore(@RequestBody MatchScore matchScore){        
        service.updateMatchScore(matchScore);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public MatchScore getMatchScore(@RequestParam("matchId") String matchId, @RequestParam("playerId") String playerId){        
        return service.getMatchScore(matchId, playerId);
    }
 
}
