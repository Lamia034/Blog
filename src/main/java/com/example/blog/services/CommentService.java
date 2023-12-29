package com.example.blog.services;

import com.example.blog.dtos.comment.CommentDto;
import com.example.blog.dtos.comment.CommentResponseDto;
import org.springframework.data.annotation.Id;

public interface CommentService {
    CommentResponseDto save(CommentDto commentDto);
}
