package com.ashutoshshrm529.jwtspring.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashutoshshrm529.jwtspring.model.User;


public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByEmail(String email);
}
