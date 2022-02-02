/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "playerScore")
public class PlayerScore {
    
    SimplyPlayer player;
    Integer totalScore;
    Integer numVotes;
    Boolean voted;

    public PlayerScore() {
    }
    
    public PlayerScore(SimplyPlayer player, Integer score, Integer numVotes, Boolean voted) {
        this.player = player;
        this.totalScore = score;
        this.numVotes = numVotes;
        this.voted = voted;
    }

    public PlayerScore(SimplyPlayer player, Integer score, Integer numVotes) {
        this.player = player;
        this.totalScore = score;
        this.numVotes = numVotes;
    }
    
    @Data
    public static class SimplyPlayer {
        String name;
        String id;

        public SimplyPlayer() {
        }
        
        public SimplyPlayer(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }
}
