package com.ashutoshshrm529.jwtspring.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ashutoshshrm529.jwtspring.model.Role;
import com.ashutoshshrm529.jwtspring.model.RoleEnum;
import com.ashutoshshrm529.jwtspring.model.User;
import com.ashutoshshrm529.jwtspring.repository.RoleRepository;
import com.ashutoshshrm529.jwtspring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class startupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${vegeta2001.app.superuserEmail}")
    private String superuserEmail;

    @Value("${vegeta2001.app.superuserPassword}")
    private String superuserPassword;
    
    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(RoleEnum.ADMIN);
        roleRepository.save(adminRole);

        Role userRole = new Role(RoleEnum.USER);
        roleRepository.save(userRole);

        User user = new User();
        user.setEmail(superuserEmail);
        user.setPassword(passwordEncoder.encode(superuserPassword));
        user.setRoles(List.of(adminRole, userRole));
        userRepository.save(user);
    }
    
}
