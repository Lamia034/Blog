package com.example.blog.services;

import com.example.blog.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Article;
import com.example.blog.repositories.ArticleRepository;

import java.util.List;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }


    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
