package com.example.src.controllers;

import com.example.src.entities.User;
import com.example.src.services.UserService;
import com.example.src.utilities.ResponseCreator;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final UserService userService;

    @RequestMapping(value = "/seedDb", method = RequestMethod.POST)
    public ResponseEntity<?> seedDb(){
        return null;
//        userService.signUpUser(new User("Admin", "Admin", "jakub23sa@wp.pl", "password"));
    }

}
