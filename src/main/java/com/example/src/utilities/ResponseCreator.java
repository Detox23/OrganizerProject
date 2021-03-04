package com.example.src.utilities;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseCreator {

    public static ResponseEntity<?> createResponseMessage(Object content, HttpStatus status){
        return new ResponseEntity<>(content, new HttpHeaders(), status);
    }



}
