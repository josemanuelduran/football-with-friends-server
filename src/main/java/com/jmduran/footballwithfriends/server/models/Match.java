/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import com.jmduran.footballwithfriends.server.enums.TeamColor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "matches")
public class Match implements Serializable {
    
    private static final long serialVersionUID = -3649857120092225060L;
    
    @Id
    String id;
    @NotNull
    String name;
    @NotNull
    Date date;
    Team team1;
    Team team2;
    List<PlayerCallUp> callUp;
    Integer numPlayers;
    Boolean openCallUp;
    String mvp;
    SimplyPlayer mister;
    Boolean cancelled;
    
    @Data
    public static class PlayerCallUp {        
        SimplyPlayer player;
        Date dateCallUp; 
    }
    
    @Data
    public static class Team {    
        List<SimplyPlayer> players;
        TeamColor color;
        Integer goals;
    }
    
    @Data
    public static class SimplyPlayer {
        String name;
        String id;
        Boolean fixed;

        public SimplyPlayer() {
        }
        
        public SimplyPlayer(String name, String id, Boolean fixed) {
            this.name = name;
            this.id = id;
            this.fixed = fixed;
        }
    }
}
