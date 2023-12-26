package com.example.blog.repositories;

import com.example.blog.entities.Article;
import com.example.blog.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ArticleRepository extends MongoRepository<Article,Long> {
}
