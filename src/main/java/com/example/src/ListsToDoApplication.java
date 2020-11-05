package com.example.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ListsToDoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListsToDoApplication.class, args);
    }

}
