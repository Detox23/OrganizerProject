package com.example.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.NetworkInterface;
import java.net.SocketException;

@Configuration
@ComponentScan({"com.example.src.configurations", "com.example.src.controllers", "com.example.src.services", "com.example.src.repositories"})
@EnableTransactionManagement
@EntityScan("com.example.src.entities")
@EnableJpaRepositories("com.example.src.repositories")
@SpringBootApplication
public class ListsToDoApplication {
    public static void main(String[] args) throws SocketException {

        SpringApplication.run(ListsToDoApplication.class, args);
    }

}
