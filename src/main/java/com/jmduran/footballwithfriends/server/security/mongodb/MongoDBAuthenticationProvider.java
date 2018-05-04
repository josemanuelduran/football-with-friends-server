/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.security.mongodb;

import com.jmduran.footballwithfriends.server.models.UserFWF;
import com.jmduran.footballwithfriends.server.repositories.IUserRepository;
import com.jmduran.footballwithfriends.server.security.fwfuserdetails.FWFUserDetails;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        FWFUserDetails loadedUser;

        try {
            UserFWF user = userRepository.findByUsername(username);
            String passwordReceived = DigestUtils.sha1Hex((String)authentication.getCredentials());
            if (user != null && user.getActive() && passwordReceived.equals(user.getPassword())) {
                List<GrantedAuthority> roles = 
                        user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.toString()))
                            .collect(Collectors.toList());
                loadedUser = new FWFUserDetails(user.getUsername(), user.getPassword(), roles);
                loadedUser.setFullName(user.getFullName());
                loadedUser.setPlayerId(user.getPlayerId());
                loadedUser.setRoles(user.getRoles());
            } else {
                throw new Exception("Invalid username or password.");
            }
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}
