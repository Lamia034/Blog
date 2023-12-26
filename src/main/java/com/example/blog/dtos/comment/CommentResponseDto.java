package com.example.blog.dtos.comment;

import com.example.blog.dtos.commentReact.CommentReactDto;
import com.example.blog.entities.Article;
import com.example.blog.entities.CommentReact;
import com.example.blog.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentResponseDto {
    private String id;
    private String text;
    private LocalDateTime postingTime;
    private Article article;
    private User user;
    private List<CommentReactDto> commentReacts;

}
