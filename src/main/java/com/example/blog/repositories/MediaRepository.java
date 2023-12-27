package com.example.blog.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository extends MongoRepository<MediaRepository , String> {
}
