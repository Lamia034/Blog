package com.example.blog.dtos.user;

import com.example.blog.dtos.article.ArticleResponseDto;
import lombok.Data;

import java.util.List;
@Data
public class UserResponseDto {

    private String id;
    private String userName;
    private String email;
    private List<ArticleResponseDto> articles;
}
