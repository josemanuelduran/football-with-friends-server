package com.jmduran.footballwithfriends.server.models;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Jose Manuel Durán
 */
@Data
@Builder
public class MatchScore {
    
    Player player;
    Float score;
    Match match;
    List<Jury> juryScores;
    
    class Jury {
        
        Player jury;
        Integer score;
        Date date;
        
    }
}
