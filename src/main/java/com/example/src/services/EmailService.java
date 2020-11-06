package com.example.src.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailService {

        private JavaMailSender emailSender;

        public void sendMessage(String to, String subject, String text){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@organizerK.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }

}
