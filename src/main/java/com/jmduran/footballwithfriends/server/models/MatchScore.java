/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "matchScore")
public class MatchScore {
    
    @Id
    String id;
    String playerId;
    String matchId;
    Date date;
    List<Score> scores;
    
    @Data
    @Builder
    public static class Score {
        
        String namePlayer;
        String idPlayer;
        Integer score;
        
    }

}
