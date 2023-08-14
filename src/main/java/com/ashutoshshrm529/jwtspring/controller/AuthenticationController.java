package com.ashutoshshrm529.jwtspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashutoshshrm529.jwtspring.communication.request.JWTLoginRequest;
import com.ashutoshshrm529.jwtspring.communication.request.JWTRefreshRequest;
import com.ashutoshshrm529.jwtspring.communication.request.UserRegisterRequest;
import com.ashutoshshrm529.jwtspring.communication.response.JWTResponse;
import com.ashutoshshrm529.jwtspring.service.auth.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(
        @RequestBody JWTLoginRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> refresh(
        @RequestBody JWTRefreshRequest request
    ) {
        return ResponseEntity.ok(service.refresh(request));
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<JWTResponse> register(
        @RequestBody UserRegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    
}
