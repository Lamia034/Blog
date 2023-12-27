package com.example.blog.dtos.article;

import com.example.blog.entities.ArticleReact;
import com.example.blog.entities.Comment;
import com.example.blog.entities.Media;
import com.example.blog.entities.User;
import com.example.blog.enumerations.mediaType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleResponseDto {
    private String id;
    private String title;
    private String text;
    private String[] tags;
    private LocalDateTime postingTime;
    private String postingTimeFormatted;
    private int nbComments;
    private int nbReacts;
    private User user;
    private List<ArticleReact> articleReacts;
    private List<Comment> comments;
    private List<Media> medias;
}
