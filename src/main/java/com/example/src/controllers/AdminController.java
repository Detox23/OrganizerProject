package com.example.src.controllers;


import com.example.src.dtos.UserForLogin;
import com.example.src.services.UserService;
import com.example.src.utilities.ResponseCreator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
