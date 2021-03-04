package com.example.src.controllers;

import com.example.src.configurations.EmailTemplates;
import com.example.src.entities.*;
import com.example.src.repositories.IConfirmationTokenRepository;
import com.example.src.repositories.IEmailTemplateRepository;
import com.example.src.repositories.IUserRepository;
import com.example.src.services.EmailService;
import com.example.src.utilities.ResponseCreator;
import lombok.AllArgsConstructor;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final IUserRepository _iUserRepository;

    private final EmailService _emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceController.class);

    private final BCryptPasswordEncoder _bCryptPasswordEncoder;

    private final IEmailTemplateRepository _iEmailTemplateRepository;

    private final IConfirmationTokenRepository _iConfirmationTokenRepository;

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/seedDb", method = RequestMethod.GET)
    public ResponseEntity<?> seedDb(){
        var isUsersSeeded = seedUsersTable();
        LOGGER.info(String.format("Was user and confirmation token table seeded? %b", isUsersSeeded));
        var isTemplatesSeeded = seedTemplates();
        LOGGER.info(String.format("Was templates table seeded? %b", isTemplatesSeeded));
        return ResponseCreator.createResponseMessage("Database seeded", HttpStatus.OK);
    }


    private boolean seedTemplates(){
        if(_iEmailTemplateRepository.count() == 0L){
            EmailTemplate emailTemplate = new EmailTemplate(UUID.randomUUID(), null, null, EmailTemplateType.ACTIVATION, EmailTemplates.getEmailTemplate());
            _iEmailTemplateRepository.save(emailTemplate);
            return true;
        }
        return false;
    }

    private boolean seedUsersTable(){
        if(_iUserRepository.count() == 0L){
            ConfirmationToken confirmationToken = new ConfirmationToken(UUID.randomUUID(), UUID.randomUUID().toString(), LocalDate.now());
            ConfirmationToken confirmationToken2 = new ConfirmationToken(UUID.randomUUID(), UUID.randomUUID().toString(), LocalDate.now());
            ConfirmationToken confirmationToken3 = new ConfirmationToken(UUID.randomUUID(), UUID.randomUUID().toString(), LocalDate.now());
            _iConfirmationTokenRepository.save(confirmationToken);
            _iConfirmationTokenRepository.save(confirmationToken2);
            _iConfirmationTokenRepository.save(confirmationToken3);
            User adminUser = new User(UUID.randomUUID(), "Admin", "Admin", "admin@gmail.com", _bCryptPasswordEncoder.encode("asd"), UserRole.ADMIN, false, true, confirmationToken, null, null);
            User anonymousUser = new User(UUID.randomUUID(), "Anonymous", "Anonymous", "anonymousUser",  _bCryptPasswordEncoder.encode("asd"), UserRole.USER, false, true, confirmationToken2,null, null);
            User normalUser = new User(UUID.randomUUID(), "User", "User", "user2@gmail.com",  _bCryptPasswordEncoder.encode("asd"), UserRole.USER, false, true, confirmationToken3, null, null);
            _iUserRepository.save(adminUser);
            _iUserRepository.save(anonymousUser);
            _iUserRepository.save(normalUser);
            return true;
        }
        return false;
    }



    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public ResponseEntity<?> healthCheck(){
        LOGGER.info(String.format("Health check method invoked on: %s %s", LocalDateTime.now().toLocalDate().toString(), LocalDateTime.now().toLocalTime().toString()));
        return ResponseCreator.createResponseMessage("Successfully health check", HttpStatus.OK);
    }

}
