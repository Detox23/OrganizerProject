package com.example.src.services;

import com.example.src.entities.EmailTemplateType;
import com.example.src.entities.User;
import com.example.src.repositories.IEmailTemplateRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final IEmailTemplateRepository _iEmailTemplateRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final Tomcat tomcat;

    public void sendConfirmationMail(User user){
        try{

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                            StandardCharsets.UTF_8.name());


            LOGGER.info(String.format("HOST: %s", tomcat.getHost().getName()));
            AtomicReference<String> url = new AtomicReference<>("");
            if(tomcat.getHost().getName().equals("localhost")){
                url.set(String.format("http://%s:%s/api/auth/%s", tomcat.getHost().getName(), "8080", user.getConfirmationToken().getConfirmationToken()));
            }else{
                url.set(String.format("http://%s/api/auth/%s", tomcat.getHost().getName()+"8080", user.getConfirmationToken().getConfirmationToken()));
            }

            helper.setTo(user.getEmail());
            helper.setText(_iEmailTemplateRepository.getByTypeIs(EmailTemplateType.ACTIVATION).getContent().replace("%URL%", url.get()), true);
            helper.setSubject("Account activation");
            helper.setFrom("jkplanner");
            javaMailSender.send(message);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

    }
}
