/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStats {
    
    Integer matchesPlayedAsBlack;
    Integer matchesPlayedAsWhite;
    Integer matchesWonAsBlack;
    Integer matchesWonAsWhite;
    Integer matchesLostAsBlack;
    Integer matchesLostAsWhite;
    Integer matchesTiedAsBlack;
    Integer matchesTiedAsWhite;
    Integer goalsScored;
    Integer goalsConceded;
    Float averageScore;
    Integer timesMVP;
    Integer timesLVP;
    
}
