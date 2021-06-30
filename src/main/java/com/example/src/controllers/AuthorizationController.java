package com.example.src.controllers;

import com.example.src.dtos.UserForCreation;
import com.example.src.dtos.UserForReturn;
import com.example.src.entities.User;
import com.example.src.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final Environment environment;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserForCreation user, HttpServletRequest request) {
        User result = userService.signUpUser(modelMapper.map(user, User.class), request.getContextPath());
        if(result == null){
            return new ResponseEntity<>(modelMapper.map("User already exists.", UserForReturn.class), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(result, UserForCreation.class), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{confirmationToken}", method = RequestMethod.GET)
    public RedirectView confirmAccount(@PathVariable String confirmationToken){
        userService.acceptUser(confirmationToken);
        var current = environment.getActiveProfiles();
        RedirectView redirectView = new RedirectView();
        if(current[0].equals("dev")){
            redirectView.setUrl("http://localhost:3000");
        }else{
            redirectView.setUrl("http://jkplanner.pl");
        }
        return redirectView;
    }

}
