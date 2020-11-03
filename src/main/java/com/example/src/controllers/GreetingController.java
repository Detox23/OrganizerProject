package com.example.src.controllers;


import com.example.src.entities.GreetingDb;
import com.example.src.repositories.GreetingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GreetingController {

    private final GreetingRepository greetingRepository;

    public GreetingController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World";
    }

    @GetMapping("/get")
    public GreetingDb get(){
        Optional<GreetingDb> result = greetingRepository.findById(Long.valueOf("1"));
        return result.isPresent() ? result.get() : null;
    }

}
