package com.example.blog.controllers;

import com.example.blog.dtos.article.ArticleDto;
import com.example.blog.dtos.article.ArticleResponseDto;
import com.example.blog.entities.Article;
import com.example.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

        private final ArticleService articleService;


        public ArticleController(ArticleService articleService) {
            this.articleService = articleService;
        }

        @PostMapping
        public ResponseEntity<ArticleResponseDto> saveArticle(@RequestBody ArticleDto articleDto) {
            ArticleResponseDto savedArticle = articleService.add(articleDto);
            return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
        }

    @GetMapping
    public List<ArticleResponseDto> getArticles(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.getAll(pageable);
    }
//    @GetMapping("/search")
//    public ResponseEntity<List<ArticleResponseDto>> searchArticles(@RequestParam String search) {
//        List<ArticleResponseDto> articleResponseDtos = articleService.searchArticles(search);
//        return ResponseEntity.ok(articleResponseDtos);
//    }
@GetMapping("/search")
public ResponseEntity<List<ArticleResponseDto>> searchArticles(@RequestParam String search,
                                                               @RequestParam int page,
                                                               @RequestParam int size) {
    Pageable pageable = PageRequest.of(page, size);
    List<ArticleResponseDto> articleResponsePage = articleService.searchArticles(search, pageable);

    return ResponseEntity.ok(articleResponsePage);
}

    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable String articleId) {
        boolean deleted = articleService.deleteArticleById(articleId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Article deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable String articleId,
            @RequestBody ArticleDto updatedArticleDto) {

        ArticleResponseDto updatedArticle = articleService.updateArticle(articleId, updatedArticleDto);

        if (updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}