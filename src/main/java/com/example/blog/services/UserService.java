package com.example.blog.services;

import com.example.blog.dtos.user.UserDto;
import com.example.blog.dtos.user.UserResponseDto;
import com.example.blog.entities.User;
import org.springframework.data.annotation.Id;

public interface UserService {
    UserResponseDto add(UserDto Element) ;
    UserResponseDto auth(UserDto Element);
}
