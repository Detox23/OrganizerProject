package com.example.src.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class GreetingController {

    @GetMapping
    public String greeting(){
        return "Hello World";
    }
}