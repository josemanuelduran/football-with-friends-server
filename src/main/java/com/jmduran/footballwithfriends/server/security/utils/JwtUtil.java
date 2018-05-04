/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.security.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static java.util.Collections.emptyList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class JwtUtil {

    public static void addAuthentication(HttpServletResponse res, String username) {

        String secret = System.getenv("FWF_TOKEN_SECRET");
        String token = Jwts.builder()
            .setSubject(username)             
            // Expiration time of 10 minutes
            .setExpiration(new Date(System.currentTimeMillis() + 600000))            
            // Hash to sing the key
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();

        //add the header to the token
        res.addHeader("Authorization", "Bearer " + token);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        
        String token = request.getHeader("Authorization");
        
        if (token != null) {
            String user = null;
            try {
                String secret = System.getenv("FWF_TOKEN_SECRET");
                user = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                        .getBody()
                        .getSubject();
            }catch (ExpiredJwtException expJwt) {
                throw new InternalAuthenticationServiceException(expJwt.getMessage());
            }

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password 
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
