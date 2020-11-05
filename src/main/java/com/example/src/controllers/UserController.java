package com.example.src.controllers;

import com.example.src.dtos.UserForCreation;
import com.example.src.entities.ConfirmationToken;
import com.example.src.entities.User;
import com.example.src.services.ConfirmationTokenService;
import com.example.src.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final ConfirmationTokenService confirmationTokenService;

    private final ModelMapper modelMapper;

    @PostMapping("sign-up/")
    public ResponseEntity<User> create(@RequestBody UserForCreation user) {
        User result = userService.signUpUser(modelMapper.map(user, User.class));
        return ResponseEntity.ok(result);
    }




}
