/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.security;

import com.jmduran.footballwithfriends.server.security.filters.JwtFilter;
import com.jmduran.footballwithfriends.server.security.filters.LoginFilter;
import com.jmduran.footballwithfriends.server.security.mongodb.MongoDBAuthenticationProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private MongoDBAuthenticationProvider authenticationProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/login/").permitAll() //permitimos el acceso a /login a cualquiera
            .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
            .and()
            // Las peticiones /login pasaran previamente por este filtro
            .addFilterBefore(new LoginFilter("/login/", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)                
            // Las demás peticiones pasarán por este filtro para validar el token
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        
        http.cors().configurationSource(new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest hsr) {
                List<String> exposedHeader = new ArrayList<>();
                exposedHeader.add("authorization");
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setExposedHeaders(exposedHeader);
                config.addAllowedOrigin("*");
                config.setAllowCredentials(true);
                return config;
            }
        });
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {        
        auth.authenticationProvider(authenticationProvider);
    }
}
