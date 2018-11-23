/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.service.impl;

import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.repositories.IPlayerRepository;
import com.jmduran.footballwithfriends.server.service.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public void createPlayer(Player player) {
        playerRepository.insert(player);
    }

    @Override
    public void deletePlayer(String playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public List<Player> getPlayers() {
        //return playerRepository.findAll();
        return playerRepository.findByActive(Boolean.TRUE);
    }

    @Override
    public Player getPlayer(String playerId) {
        return playerRepository.findById(playerId).get();
    }
}
