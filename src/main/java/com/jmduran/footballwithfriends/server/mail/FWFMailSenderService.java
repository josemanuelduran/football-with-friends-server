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
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class FWFMailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private PlayerService playerService;
    
    public void sendMail(String to, String subject, String body) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(mail);
        } catch (MessagingException ex) {
        }
    }
    
    public void sendMailToAll(String subject, String body) {
        List<Player> players = playerService.getPlayers();
        players.stream().forEach(player -> {
            String email = player.getEmail();
            if (email != null && !email.equals("")){
                try {
                    MimeMessage mail = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                    helper.setTo(email);
                    helper.setSubject(subject);
                    helper.setText(body, true);
                    mailSender.send(mail);
                } catch (MessagingException ex) {
                }
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
                    try {
                        MimeMessage mail = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                        helper.setTo(email);
                        helper.setSubject(subject);
                        helper.setText(body, true);
                        mailSender.send(mail);
                    } catch (MessagingException ex) {
                    } 
                }
        });
    }
}
