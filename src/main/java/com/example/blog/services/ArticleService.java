package com.example.blog.services;

import com.example.blog.dtos.article.ArticleDto;
import com.example.blog.dtos.article.ArticleResponseDto;
import com.example.blog.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Article;
import com.example.blog.repositories.ArticleRepository;

import java.util.List;



public interface ArticleService{

    ArticleResponseDto add(ArticleDto articleDto);
    List<ArticleResponseDto> getAll(Pageable pageable);
    boolean deleteArticleById(String articleId);
}
