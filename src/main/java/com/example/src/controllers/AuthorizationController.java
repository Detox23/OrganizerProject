package com.example.src.controllers;

import com.example.src.dtos.UserForCreation;
import com.example.src.dtos.UserForLogin;
import com.example.src.entities.User;
import com.example.src.services.ConfirmationTokenService;
import com.example.src.services.UserService;
import com.example.src.utilities.ResponseCreator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final UserService userService;

    private final ConfirmationTokenService confirmationTokenService;

    private final ModelMapper modelMapper;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserForCreation user) {
        User result = userService.signUpUser(modelMapper.map(user, User.class));
        return new ResponseEntity<>(modelMapper.map(result, UserForCreation.class), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserForLogin user){
        return ResponseCreator.createResponseMessage(null, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{confirmationToken}")
    public ResponseEntity<?> confirmAccount(@PathVariable String confirmationToken){
        return ResponseCreator.createResponseMessage(null, HttpStatus.NO_CONTENT);
    }

}
