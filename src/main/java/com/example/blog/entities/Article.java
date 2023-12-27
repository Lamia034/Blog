package com.example.blog.entities;

import com.example.blog.annotations.CostumDateTime;
import com.example.blog.enumerations.mediaType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "article")
@Data
public class Article {
        @Id
        private String id;
        private String title;
        private String text;
      //  @JsonSerialize(using = CostumDateTime.class)
        private LocalDateTime postingTime;
        private int nbComments;
        private int nbReacts;
        private String[] tags;
        private User user;
        private List<ArticleReact> articleReacts;
        private List<Comment> comments;
        private List<Media> medias;
    }
