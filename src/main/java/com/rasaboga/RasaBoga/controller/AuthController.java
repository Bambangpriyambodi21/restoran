package com.rasaboga.RasaBoga.controller;

import com.rasaboga.RasaBoga.entity.UserCredential;
import com.rasaboga.RasaBoga.model.request.AuthRequest;
import com.rasaboga.RasaBoga.model.response.UserResponse;
import com.rasaboga.RasaBoga.model.response.WebResponse;
import com.rasaboga.RasaBoga.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        UserResponse register = authService.register(request);
        WebResponse webResponse = WebResponse.builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create new user")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }

    @PostMapping(path = "/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request){
        UserResponse register = authService.registerAdmin(request);
        WebResponse webResponse = WebResponse.builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create new user")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        String login = authService.login(request);
        WebResponse webResponse = WebResponse.builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create new user")
                .data(login)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }
}
