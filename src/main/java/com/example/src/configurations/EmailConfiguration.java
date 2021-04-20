package com.example.src.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class EmailConfiguration {

    @Value("${spring.mail.username}")
    public String awsAccessKey;

    @Value("${spring.mail.password}")
    public String awsSecretKey;

}
