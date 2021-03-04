package com.example.src.services;

import com.example.src.entities.EmailTemplateType;
import com.example.src.entities.User;
import com.example.src.repositories.IEmailTemplateRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final IEmailTemplateRepository _iEmailTemplateRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final Tomcat tomcat;

    public void sendConfirmationMail(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        LOGGER.info(String.format("HOST: %s", tomcat.getHost().getName()));
        AtomicReference<String> url = new AtomicReference<>("");
        if(tomcat.getHost().getName() == "localhost"){

            url.set(String.format("http://%s:%s/api/auth/%s", tomcat.getHost().getName(), "8080", user.getConfirmationToken().getConfirmationToken()));
        }else{
            url.set(String.format("http://%s/api/auth/%s", tomcat.getHost().getName(), "8080", user.getConfirmationToken().getConfirmationToken()));
        }
        message.setFrom("Planner");
        message.setText(_iEmailTemplateRepository.getByTypeIs(EmailTemplateType.ACTIVATION).getContent().replace("%URL%", url.get()));
        message.setTo(user.getEmail());
        message.setSubject("Account activation");
        javaMailSender.send(message);
    }
}
