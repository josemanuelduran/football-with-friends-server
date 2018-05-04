/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service;

import com.jmduran.footballwithfriends.server.models.Player;
import java.util.List;

public interface PlayerService {
    
    void createPlayer(Player player);
    void deletePlayer(String playerId);
    void updatePlayer(Player player);    
    List<Player> getPlayers();
    Player getPlayer(String playerId);   
    
}
