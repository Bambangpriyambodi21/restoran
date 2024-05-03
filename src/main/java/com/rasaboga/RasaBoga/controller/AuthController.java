package com.rasaboga.RasaBoga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(){
        return null;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(){
        return null;
    }
}
