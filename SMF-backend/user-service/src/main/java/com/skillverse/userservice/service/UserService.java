package com.skillverse.userservice.service;

import com.skillverse.userservice.model.User;
import com.skillverse.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public User createUser(User user) {
       // Optional: check if username/email already exists
       return userRepository.save(user);
   }

   public List<User> getAllUsers() {
       return userRepository.findAll();
   }

   public Optional<User> getUserById(String id) {
       return userRepository.findById(id);
   }

   public Optional<User> getUserByUsername(String username) {
       return userRepository.findByUsername(username);
   }

   public void deleteUser(String id) {
       userRepository.deleteById(id);
   }

   public User updateUser(String id, User updatedUser) {
       return userRepository.findById(id).map(user -> {
           user.setUsername(updatedUser.getUsername());
           user.setEmail(updatedUser.getEmail());
           user.setPassword(updatedUser.getPassword());
           user.setBio(updatedUser.getBio());
           user.setProfilePictureUrl(updatedUser.getProfilePictureUrl());
           return userRepository.save(user);
       }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
   }
}