package com.example.blog.entities;

import com.example.blog.entities.abstracts.React;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection="article_react")
public class ArticleReact extends React {
    private Article article;
}
