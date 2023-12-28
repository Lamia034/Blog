package com.example.blog.dtos.article;

import com.example.blog.annotations.CostumDateTime;
import com.example.blog.enumerations.mediaType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDto {
    private String id;
    private String title;
    private String text;
    private LocalDateTime postingTime;
    private String[] tags;
  private String mediaId;
//    private String[] mediaIds;
//private List<String> mediaIds;
    private String userId;
}
