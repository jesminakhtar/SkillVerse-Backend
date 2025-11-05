package com.skillverse.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users") // Table name
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false, unique = true)
    private String username;
    
    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String  name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    private String bio; // Optional field for user bio

    private String profilePictureUrl; // Optional field for profile picture URL
}