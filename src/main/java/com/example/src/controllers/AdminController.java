package com.example.src.controllers;


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
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @RequestMapping(value = "/blockUser/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> login(@PathVariable String id){
        var result = userService.blockUser(id);
        return ResponseCreator.createResponseMessage(result, HttpStatus.NO_CONTENT);
    }
}
