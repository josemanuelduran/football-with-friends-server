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
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (user.getPassword() == null) {
            UserFWF userOrigin = userRepository.findById(user.getId()).get();
            user.setPassword(userOrigin.getPassword());
        }
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

    @Override
    public void changePassword(String userId, String token) {
        UserFWF user = userRepository.findById(userId).get();
        if (user != null) {
            String unencryptedToken = new String(Base64.getDecoder().decode(token),
                    Charset.forName("UTF-8"));
            // unencryptedToken = oldPassword:newPassword
            final String[] values = unencryptedToken.split(":",2);
            String oldEncryptedPassword = DigestUtils.sha1Hex(values[0]);
            if (oldEncryptedPassword.equals(user.getPassword())) {
                String newEncryptedPassword = DigestUtils.sha1Hex(values[1]);
                user.setPassword(newEncryptedPassword);
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Current passord invalid");
            }
        }
    }
}
