package com.example.blog.dtos.user;

import com.example.blog.entities.Article;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String userName;
    private String email;
    private String password;
}
