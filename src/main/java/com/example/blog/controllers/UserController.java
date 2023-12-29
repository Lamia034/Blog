package com.example.blog.controllers;

import com.example.blog.dtos.user.UserDto;
import com.example.blog.dtos.user.UserResponseDto;
import com.example.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody UserDto user) {
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("user", userService.add(user));
            result.put("message", "user created successfully");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/auth")
    public ResponseEntity<Map<String, Object>> auth(@RequestBody UserDto user) {
        Map<String, Object> result = new HashMap<>();
        UserResponseDto authenticatedUser = userService.auth(user);
        if (authenticatedUser != null) {
            result.put("user", authenticatedUser);
            result.put("message", "User authenticated successfully");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.put("message", "Authentication failed. Invalid username or password.");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}