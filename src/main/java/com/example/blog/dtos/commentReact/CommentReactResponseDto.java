package com.example.blog.dtos.commentReact;

import com.example.blog.entities.Comment;
import lombok.Data;

@Data
public class CommentReactResponseDto {
    private Comment comment;
}
