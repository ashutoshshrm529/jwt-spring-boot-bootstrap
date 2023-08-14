package com.vegeta2001.jwtspring.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vegeta2001.jwtspring.model.Role;
import com.vegeta2001.jwtspring.model.RoleEnum;


public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleEnum name);
}
