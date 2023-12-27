package com.example.blog.repositories;

import com.example.blog.entities.CommentReact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentReactRepository extends MongoRepository<CommentReact , String> {
}
