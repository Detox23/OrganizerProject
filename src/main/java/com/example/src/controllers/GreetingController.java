package com.example.src.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greeting(){
        return "Hello World";
    }



}
