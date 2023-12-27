package com.example.blog.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection="comment")
public class Comment {
    @Id
    private String id;
    private String text;
    @CreatedDate
    private LocalDateTime postingTime;
    private Article article;
    private User user;
    private List<CommentReact> commentReacts;
}
