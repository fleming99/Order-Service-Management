package com.fleming99.authentication_microservice.controller;

import com.fleming99.authentication_microservice.application.AuthorizationServiceImpl;
import com.fleming99.authentication_microservice.core.dto.AuthenticationRequest;
import com.fleming99.authentication_microservice.core.dto.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthorizationServiceImpl authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return authorizationService.login(authenticationRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest){
        return authorizationService.register(signupRequest);
    }

}
