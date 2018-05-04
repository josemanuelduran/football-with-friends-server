/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service;

import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Player;
import java.util.List;

public interface MatchService {
    
    void createMatch(Match match);
    void deleteMatch(String matchId);
    void updateMatch(Match match);    
    List<Match> getMatchs();
    Match getMatch(String matchId); 
    void joinPlayerCallUp(String matchId, Player player);
    void unJoinPlayerCallUp(String matchId, String playerId);
    void updateTeams(String matchId, List<Match.Team> teams);
    
}
