package com.vegeta2001.jwtspring.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
        @RequestBody RefreshRequest request
    ) {
        return ResponseEntity.ok(service.refresh(request));
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    
}
