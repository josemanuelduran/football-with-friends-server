package com.jmduran.footballwithfriends.server.models;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Jose Manuel Durán
 */
@Data
@Builder
public class Score {
    
    Player player;
    Float score;
    Date dateScore;
    
}
