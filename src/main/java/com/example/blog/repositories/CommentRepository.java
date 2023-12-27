package com.example.blog.repositories;

import com.example.blog.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment , String> {
}
