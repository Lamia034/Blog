package com.example.blog.repositories;

import com.example.blog.entities.ArticleReact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleReactRepository extends MongoRepository<ArticleReact, String> {
}
