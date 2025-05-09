package com.skillverse.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "users")
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

   // getters & setters

   public String getId() { return id; }
   public void setId(String id) { this.id = id; }

   public String getUsername() { return username; }
   public void setUsername(String username) { this.username = username; }

   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }

   public String getPassword() { return password; }
   public void setPassword(String password) { this.password = password; }

   public String getBio() { return bio; }
   public void setBio(String bio) { this.bio = bio; }

   public String getProfilePictureUrl() { return profilePictureUrl; }
   public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
}