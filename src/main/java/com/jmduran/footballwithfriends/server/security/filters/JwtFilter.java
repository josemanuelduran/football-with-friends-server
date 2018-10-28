/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.security.filters;

import com.jmduran.footballwithfriends.server.security.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 */
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        try {
            Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredJwtException expJwt) {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, expJwt.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
