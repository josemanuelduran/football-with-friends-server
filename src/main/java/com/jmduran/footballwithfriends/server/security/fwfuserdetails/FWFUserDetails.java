/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.security.fwfuserdetails;

import com.jmduran.footballwithfriends.server.enums.Role;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
public class FWFUserDetails extends User {
    
    private static final long serialVersionUID = 5536030671771512213L;

    public FWFUserDetails(String username, String password,
         Collection<? extends GrantedAuthority> authorities) {            
        super(username, password, authorities);
    }

    String id;
    String fullName;
    List<Role> roles;
    String playerId;

}
