package com.example.blog.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String email;
    private List<Article> articles;
    private List<Comment> comments;
    private List<ArticleReact> articleReacts;
    private List<CommentReact> commentReacts;

}
