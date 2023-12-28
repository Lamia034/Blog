package com.example.blog.entities;

import com.example.blog.enumerations.mediaType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="media")
public class Media {
    @Id
    private String id;
    private String url;
    private mediaType mediaType;
    private Article article;
}
