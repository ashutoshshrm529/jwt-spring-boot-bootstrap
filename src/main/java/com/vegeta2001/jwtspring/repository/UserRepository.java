package com.vegeta2001.jwtspring.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vegeta2001.jwtspring.model.User;


public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByEmail(String email);
}
