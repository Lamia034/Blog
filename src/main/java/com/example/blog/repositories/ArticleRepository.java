package com.example.blog.repositories;

import com.example.blog.entities.Article;
import com.example.blog.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository extends MongoRepository<Article,Long> {
    Optional<Article> findById(ObjectId objectId);
    List<Article> findByTagsContainingOrTextContainingOrTitleContaining(String search, String search1, String search2);
}
