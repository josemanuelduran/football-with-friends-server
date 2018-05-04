/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import com.jmduran.footballwithfriends.server.enums.Role;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "users")
public class UserFWF implements Serializable {
    
    private static final long serialVersionUID = -8444932293171151748L;
    
    @Id
    String id;
    @NotNull
    Boolean active;
    @NotNull
    String username;
    @NotNull
    String password;
    String fullName;
    List<Role> roles;
    String playerId;
    
}
