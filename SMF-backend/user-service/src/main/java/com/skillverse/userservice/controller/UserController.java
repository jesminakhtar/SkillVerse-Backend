package com.skillverse.userservice.controller;

import com.skillverse.userservice.model.User;
import com.skillverse.userservice.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

   @Autowired
   private UserService userService;

   @PostMapping
   public User createUser(@Valid @RequestBody User user) {
       return userService.createUser(user);
   }

   @GetMapping
   public List<User> getAllUsers() {
       return userService.getAllUsers();
   }

   @GetMapping("/{username}")
   public Optional<User> getUserByUserName(@PathVariable String username) {
       return userService.getUserByUsername(username);
   }
   
   @GetMapping("/email/{email}")
   public Optional<User> getUserByEmail(@PathVariable String email) {
       return userService.getUserByEmail(email);
   }

   @PutMapping("/{username}")
   public User updateUser(@PathVariable String username, @Valid @RequestBody User user) {
       return userService.updateUser(username, user);
   }

   @DeleteMapping("/{id}")
   public void deleteUser(@PathVariable String id) {
       userService.deleteUser(id);
   }
}