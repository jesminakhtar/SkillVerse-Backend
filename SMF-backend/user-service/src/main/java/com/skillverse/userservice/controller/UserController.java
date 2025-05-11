package com.skillverse.userservice.controller;

import com.skillverse.userservice.model.User;
import com.skillverse.userservice.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
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

   @GetMapping("/{id}")
   public Optional<User> getUserById(@PathVariable String id) {
       return userService.getUserById(id);
   }

   @PutMapping("/{id}")
   public User updateUser(@PathVariable String id, @Valid @RequestBody User user) {
       return userService.updateUser(id, user);
   }

   @DeleteMapping("/{id}")
   public void deleteUser(@PathVariable String id) {
       userService.deleteUser(id);
   }
}