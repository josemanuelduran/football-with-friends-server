/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import com.jmduran.footballwithfriends.server.enums.Position;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "players")
public class Player implements Serializable {
    
    private static final long serialVersionUID = -6576852772935229343L;
        
    @Id
    String id; 
    @NotNull
    String alias;        
    String fullName;
    Double currentScore;
    Position mainPosition;
    Position secondaryPosition;
    Position tertiaryPosition;
    @NotNull
    Boolean fixed;
    Date registerDate;
    Date fixedDate;
    @NotNull
    Boolean active;
    String userId;
    String phoneNumber;
    String email;
    
    
}
