package com.vegeta2001.jwtspring.service.auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vegeta2001.jwtspring.communication.request.JWTLoginRequest;
import com.vegeta2001.jwtspring.communication.request.JWTRefreshRequest;
import com.vegeta2001.jwtspring.communication.request.UserRegisterRequest;
import com.vegeta2001.jwtspring.communication.response.JWTResponse;
import com.vegeta2001.jwtspring.model.RoleEnum;
import com.vegeta2001.jwtspring.model.User;
import com.vegeta2001.jwtspring.repository.RoleRepository;
import com.vegeta2001.jwtspring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JWTResponse login(JWTLoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        
        return authResponse(user);
    }

    public JWTResponse refresh(JWTRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            User user = repository.findByEmail(userEmail).orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                return authRefreshResponse(user, refreshToken);
            }
        }

        return null;
    }

    public JWTResponse register(UserRegisterRequest request) {
        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(List.of(roleRepository.findByName(RoleEnum.USER).orElseThrow()))
            .build();

        repository.save(user);

        return authResponse(user);
    }

    private JWTResponse authResponse(User user) {
        String jwtRefreshToken = jwtService.generateRefreshToken(user);

        return authRefreshResponse(user, jwtRefreshToken);
    }

    private JWTResponse authRefreshResponse(User user, String refreshToken) {
        String jwtToken = jwtService.generateToken(user);

        return JWTResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }

}
