/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmduran.footballwithfriends.server.enums.Role;
import com.jmduran.footballwithfriends.server.security.fwfuserdetails.FWFUserDetails;
import com.jmduran.footballwithfriends.server.security.utils.JwtUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        final String authorization = req.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));
            // credentials = username:password
            final String[] values = credentials.split(":",2);
                
            // Finalmente autenticamos
            // Spring comparará el user/password recibidos
            // contra el que definimos en la clase SecurityConfig
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            values[0],
                            values[1],
                            Collections.emptyList()
                    )
            );
        } else {
            throw new InternalAuthenticationServiceException("Basic Authorization header is required.");
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        // if the authentication was successful, we add the token in the response
        JwtUtil.addAuthentication(res, auth.getName());
        res.setContentType("application/json");
        FWFUserDetails userDetails = (FWFUserDetails)auth.getPrincipal();
        UserResponse userResponse = new UserResponse();
        userResponse.setFullName(userDetails.getFullName());
        userResponse.setPlayerId(userDetails.getPlayerId());
        userResponse.setUsername(userDetails.getUsername());
        userResponse.setRoles(userDetails.getRoles());
        res.getWriter().write(new ObjectMapper().writeValueAsString(userResponse));
    }
    
    @Data
    class UserResponse {
        String username;
        String fullName;
        String playerId;
        List<Role> roles;
    }
}
