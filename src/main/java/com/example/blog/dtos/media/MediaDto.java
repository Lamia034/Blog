package com.example.blog.dtos.media;

import com.example.blog.entities.Article;
import com.example.blog.enumerations.mediaType;
import lombok.Data;

@Data
public class MediaDto {
    private String id;
    private String url;
    private mediaType mediaType;
//    private String articleId;
}
