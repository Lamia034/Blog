package com.example.blog.services;

import com.example.blog.dtos.article.ArticleDto;
import com.example.blog.dtos.article.ArticleResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;



public interface ArticleService{

    ArticleResponseDto add(ArticleDto articleDto);
    List<ArticleResponseDto> getAll(Pageable pageable);
    boolean deleteArticleById(String articleId);
    List<ArticleResponseDto> searchArticles(String search);
    ArticleResponseDto updateArticle(String articleId,ArticleDto updatedArticleDto);
}
