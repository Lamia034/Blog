package com.example.blog.dtos.article;

import com.example.blog.enumerations.mediaType;
import lombok.Data;

@Data
public class ArticleDto {
    private String id;
    private String title;
    private String text;
    private mediaType media;
    private int nbComments;
    private int nbReacts;
    private String userId;
}
