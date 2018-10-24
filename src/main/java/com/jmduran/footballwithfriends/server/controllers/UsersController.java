/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.controllers;

import com.jmduran.footballwithfriends.server.models.UserFWF;
import com.jmduran.footballwithfriends.server.service.UserService;
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
import org.apache.commons.codec.digest.DigestUtils;

@RestController
@RequestMapping("/fwf/user")
public class UsersController {
    
    @Autowired
    private UserService service;
    
    @RequestMapping(value = "", 
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserFWF user){
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        service.createUser(user);
    }
    
    @RequestMapping(value = "/{userId}", 
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId){        
        service.deleteUser(userId);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserFWF user){  
        if (user.getPassword() != null) {
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        }
        service.updateUser(user);
    }
    
    @RequestMapping(value = "", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserFWF> getUsers(){        
        return service.getUsers();
    }
    
    @RequestMapping(value = "/{userId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserFWF> getUser(@PathVariable String userId){        
        UserFWF user = service.getUser(userId);
        ResponseEntity<UserFWF> response;
        if (user == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(user, HttpStatus.OK);            
        }        
        return response;
    }        
}
