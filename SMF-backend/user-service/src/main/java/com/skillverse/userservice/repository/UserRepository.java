package com.skillverse.userservice.repository;

import com.skillverse.userservice.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
   Optional<User> findByUsername(String username);
   Optional<User> findByEmail(String email);
}