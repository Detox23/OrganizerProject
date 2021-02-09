package com.example.src.controllers;

import com.example.src.dtos.UserForCreation;
import com.example.src.entities.User;
import com.example.src.services.UserService;
import com.example.src.utilities.ResponseCreator;
import lombok.AllArgsConstructor;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final UserService _userService;

    private final ModelMapper _modelMapper;

    @RequestMapping(value = "/seedDb", method = RequestMethod.POST)
    public ResponseEntity<?> seedDb(){
        UserForCreation userForCreation = new UserForCreation("Admin", "Admin", "jakub23sa@wp.pl", "password");
        var _mappedDbUser = _modelMapper.map(userForCreation, User.class);
        var createdUser = _userService.signUpUser(_mappedDbUser);
        return ResponseCreator.createResponseMessage(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public ResponseEntity<?> healCheck(){
        return ResponseCreator.createResponseMessage("Hello", HttpStatus.OK);
    }

}
