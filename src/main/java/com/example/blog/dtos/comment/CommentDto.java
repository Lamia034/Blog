package com.example.blog.dtos.comment;

import com.example.blog.entities.Article;
import com.example.blog.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private String id;
    private String text;
    private LocalDateTime postingTime;
    private Article article;
    private String userId;
}
