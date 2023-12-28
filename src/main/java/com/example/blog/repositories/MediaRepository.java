package com.example.blog.repositories;

import com.example.blog.entities.Article;
import com.example.blog.entities.Media;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MediaRepository extends MongoRepository<Media, String> {
    Optional<Media> findById(ObjectId objectId);
}
