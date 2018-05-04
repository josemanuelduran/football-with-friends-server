/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.models.UserFWF;
import com.jmduran.footballwithfriends.server.repositories.IUserRepository;
import com.jmduran.footballwithfriends.server.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void createUser(UserFWF user) {
        userRepository.insert(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(UserFWF user) {
        userRepository.save(user);
    }

    @Override
    public List<UserFWF> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserFWF getUser(String userId) {
        return userRepository.findById(userId).get();
    }
}
