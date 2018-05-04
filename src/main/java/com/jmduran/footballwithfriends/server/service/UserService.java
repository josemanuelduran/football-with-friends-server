/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service;

import com.jmduran.footballwithfriends.server.models.UserFWF;
import java.util.List;

public interface UserService {
    
    void createUser(UserFWF user);
    void deleteUser(String userId);
    void updateUser(UserFWF user);    
    List<UserFWF> getUsers();
    UserFWF getUser(String userId);   
    
}
