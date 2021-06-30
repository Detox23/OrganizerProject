package com.example.src.controllers;

import com.example.src.services.ConfirmationTokenService;
import com.example.src.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService _userService;

    private final ConfirmationTokenService _confirmationTokenService;

    private final ModelMapper _modelMapper;

}
