package com.example.blog.dtos.user;

import com.example.blog.dtos.article.ArticleResponseDto;
import com.example.blog.entities.Article;
import com.example.blog.entities.ArticleReact;
import com.example.blog.entities.Comment;
import com.example.blog.entities.CommentReact;
import lombok.Data;

import java.util.List;
@Data
public class UserResponseDto {

    private String userName;
    private String password;
    private String email;
    private List<Article> articles;
    private List<Comment> comments;
    private List<ArticleReact> articleReacts;
    private List<CommentReact> commentReacts;
}
