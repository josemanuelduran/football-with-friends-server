/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.mail;

import com.jmduran.footballwithfriends.server.models.Match;
import com.jmduran.footballwithfriends.server.models.Player;
import com.jmduran.footballwithfriends.server.service.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class FWFMailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private PlayerService playerService;
    
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body); 
            mailSender.send(message);
    }
    
    public void sendMailToAll(String subject, String body) {
        List<Player> players = playerService.getPlayers();
        players.stream().forEach(player -> {
            String email = player.getEmail();
            if (email != null && !email.equals("")){
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject(subject);
                message.setText(body); 
                mailSender.send(message); 
            }
        });
    }
    
    public void sendMailToMatchPlayers(Match match, String subject, String body) {
        List<Player> players = playerService.getPlayers();
        players
            .stream()
            .filter(player -> match.getCallUp().stream()
                    .anyMatch(playerMatch -> playerMatch.getPlayer().getId().equals(player.getId())))
            .forEach(playerMatch -> {
                String email = playerMatch.getEmail();
                if (email != null && !email.equals("")){
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(email);
                    message.setSubject(subject);
                    message.setText(body); 
                    mailSender.send(message); 
                }
        });
    }
}
