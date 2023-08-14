package com.ashutoshshrm529.jwtspring.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashutoshshrm529.jwtspring.model.Role;
import com.ashutoshshrm529.jwtspring.model.RoleEnum;


public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleEnum name);
}
