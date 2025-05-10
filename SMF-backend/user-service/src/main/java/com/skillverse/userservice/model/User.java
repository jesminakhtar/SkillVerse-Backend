package com.skillverse.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Document(collection = "users")
@Data
public class User {

   @Id
   private String id;

   @NotBlank(message = "Username is mandatory")
   private String username;

   @Email(message = "Invalid email format")
   private String email;

   @NotBlank(message = "Password is mandatory")
   private String password;

   private String bio;
   private String profilePictureUrl;

}