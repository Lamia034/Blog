package com.example.blog.services.impl;

import com.example.blog.dtos.article.ArticleDto;
import com.example.blog.dtos.article.ArticleResponseDto;
import com.example.blog.entities.Article;
import com.example.blog.repositories.ArticleRepository;
import com.example.blog.services.ArticleService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ArticleResponseDto add(ArticleDto articleDto) {
        try {

            Article article = modelMapper.map(articleDto, Article.class);
            article.setPostingTime(LocalDateTime.now());
            Article savedArticle = articleRepository.save(article);
            return modelMapper.map(savedArticle, ArticleResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save article: " + e.getMessage());
        }
    }

@Override
@Transactional
public List<ArticleResponseDto> getAll(Pageable pageable) {
    try {
        Page<Article> articlePage = articleRepository.findAll(pageable);
        List<Article> articles = articlePage.getContent();
        List<ArticleResponseDto> responseDtoList = new ArrayList<>();

        for (Article article : articles) {
            LocalDateTime postingTime = article.getPostingTime();
            if (postingTime != null) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                Duration duration = Duration.between(postingTime, currentDateTime);

                String timeDifference;
                if (duration.toHours() >= 2) {
                    timeDifference = duration.toHours() + " hours ago";
                } else if (duration.toHours() >= 1) {
                    timeDifference = "1 hour ago";
                } else if (duration.toMinutes() >= 2) {
                    timeDifference = duration.toMinutes() + " minutes ago";
                } else if (duration.toMinutes() >= 1) {
                    timeDifference = "1 minute ago";
                } else {
                    timeDifference = "just now";
                }

                ArticleResponseDto responseDto = modelMapper.map(article, ArticleResponseDto.class);
                responseDto.setPostingTimeFormatted(timeDifference);
                responseDtoList.add(responseDto);
            }
        }

        return responseDtoList;
    } catch (Exception e) {
        throw new RuntimeException("Failed to retrieve articles: " + e.getMessage(), e);
    }
}


    @Override
    @Transactional
    public boolean deleteArticleById(String articleId) {
        try {
            ObjectId objectId = new ObjectId(articleId);
            Optional<Article> articleOptional = articleRepository.findById(objectId);
            if (articleOptional.isPresent()) {
                articleRepository.delete(articleOptional.get());
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid article ID format", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete article: " + e.getMessage());
        }
    }


}
