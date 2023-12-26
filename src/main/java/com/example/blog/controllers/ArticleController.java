package com.example.blog.controllers;

import com.example.blog.entities.Article;
import com.example.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

        private final ArticleService articleService;

        @Autowired
        public ArticleController(ArticleService articleService) {
            this.articleService = articleService;
        }

        @PostMapping
        public void createArticle(@RequestBody Article article) {
            articleService.saveArticle(article);
        }
    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    }