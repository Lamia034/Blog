package com.example.blog.services.impl;

import com.example.blog.dtos.article.ArticleDto;
import com.example.blog.dtos.article.ArticleResponseDto;
import com.example.blog.dtos.media.MediaDto;
import com.example.blog.entities.Article;
import com.example.blog.entities.Media;
import com.example.blog.enumerations.mediaType;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.repositories.ArticleRepository;
import com.example.blog.repositories.MediaRepository;
import com.example.blog.services.ArticleService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final MediaRepository mediaRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository,MediaRepository mediaRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.mediaRepository = mediaRepository;
    }

    @Override
    @Transactional
    public ArticleResponseDto add(ArticleDto articleDto) {
        try {

            Article article = modelMapper.map(articleDto, Article.class);
            article.setPostingTime(LocalDateTime.now());

            String mediaId = articleDto.getMediaId();
            if (mediaId != null && !mediaId.isEmpty()) {
                Media media = mediaRepository.findById(mediaId)
                        .orElseThrow(() -> new RuntimeException("Media not found with id: " + mediaId));
                article.setMedias(Collections.singletonList(media));
            }
            Article savedArticle = articleRepository.save(article);
            return modelMapper.map(savedArticle, ArticleResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save article: " + e.getMessage());
        }
    }

@Override
@Transactional
//public List<ArticleResponseDto> getAll(Pageable pageable) {
//    try {
//        Page<Article> articlePage = articleRepository.findAll(pageable);
//        List<Article> articles = articlePage.getContent();
//        List<ArticleResponseDto> responseDtoList = new ArrayList<>();
//
//        for (Article article : articles) {
//            LocalDateTime postingTime = article.getPostingTime();
//            if (postingTime != null) {
//                LocalDateTime currentDateTime = LocalDateTime.now();
//                Duration duration = Duration.between(postingTime, currentDateTime);
//
//                String timeDifference;
//                if (duration.toHours() >= 2) {
//                    timeDifference = duration.toHours() + " hours ago";
//                } else if (duration.toHours() >= 1) {
//                    timeDifference = "1 hour ago";
//                } else if (duration.toMinutes() >= 2) {
//                    timeDifference = duration.toMinutes() + " minutes ago";
//                } else if (duration.toMinutes() >= 1) {
//                    timeDifference = "1 minute ago";
//                } else {
//                    timeDifference = "just now";
//                }
//
//                ArticleResponseDto responseDto = modelMapper.map(article, ArticleResponseDto.class);
//                responseDto.setPostingTimeFormatted(timeDifference);
//                responseDtoList.add(responseDto);
//            }
//        }
//
//        return responseDtoList;
//    } catch (Exception e) {
//        throw new RuntimeException("Failed to retrieve articles: " + e.getMessage(), e);
//    }
//}

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
                if (duration.toHours() >= 24) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String createdDate = postingTime.format(formatter);
                    timeDifference = "Created: " + createdDate;
                } else if (duration.toHours() >= 2) {
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

@Override
@Transactional
//public List<ArticleResponseDto> searchArticles(String search) {
//    List<Article> articles;
//    List<ArticleResponseDto> articleResponseDtos = new ArrayList<>();
//
//    if (search != null) {
//        articles = articleRepository.findByTagsContainingOrTextContainingOrTitleContaining(search, search, search);
//        for (Article article : articles) {
//            ArticleResponseDto articleResponseDto = modelMapper.map(article, ArticleResponseDto.class);
//            articleResponseDtos.add(articleResponseDto);
//        }
//    }
//
//    return articleResponseDtos;
//}


public List<ArticleResponseDto> searchArticles(String search, Pageable pageable) {
    try {
        List<ArticleResponseDto> responseDtoList = new ArrayList<>();

        if (search != null) {
            Page<Article> articlesPage = articleRepository.findByTagsContainingOrTextContainingOrTitleContaining(
                    search, search, search, pageable);

            List<Article> articles = articlesPage.getContent();

            for (Article article : articles) {
                LocalDateTime postingTime = article.getPostingTime();
                if (postingTime != null) {
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    Duration duration = Duration.between(postingTime, currentDateTime);

                    String timeDifference;
                    if (duration.toHours() >= 24) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String createdDate = postingTime.format(formatter);
                        timeDifference = "Created: " + createdDate;
                    } else if (duration.toHours() >= 2) {
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
        }

        return responseDtoList;
    } catch (Exception e) {
        throw new RuntimeException("Failed to search articles: " + e.getMessage(), e);
    }
}


//public List<ArticleResponseDto> searchArticles(String search, Pageable pageable) {
//    try {
//        List<ArticleResponseDto> responseDtoList = new ArrayList<>();
//
//        if (search != null) {
//            Page<Article> articlesPage = articleRepository.findByTagsContainingOrTextContainingOrTitleContaining(
//                    search, search, search, pageable);
//
//            List<Article> articles = articlesPage.getContent();
//
//            for (Article article : articles) {
//                LocalDateTime postingTime = article.getPostingTime();
//                if (postingTime != null) {
//                    LocalDateTime currentDateTime = LocalDateTime.now();
//                    Duration duration = Duration.between(postingTime, currentDateTime);
//
//                    String timeDifference;
//                    if (duration.toHours() >= 2) {
//                        timeDifference = duration.toHours() + " hours ago";
//                    } else if (duration.toHours() >= 1) {
//                        timeDifference = "1 hour ago";
//                    } else if (duration.toMinutes() >= 2) {
//                        timeDifference = duration.toMinutes() + " minutes ago";
//                    } else if (duration.toMinutes() >= 1) {
//                        timeDifference = "1 minute ago";
//                    } else {
//                        timeDifference = "just now";
//                    }
//
//                    ArticleResponseDto responseDto = modelMapper.map(article, ArticleResponseDto.class);
//                    responseDto.setPostingTimeFormatted(timeDifference);
//                    responseDtoList.add(responseDto);
//                }
//            }
//        }
//
//        return responseDtoList;
//    } catch (Exception e) {
//        throw new RuntimeException("Failed to search articles: " + e.getMessage(), e);
//    }
//}



    @Override
    @Transactional
    public ArticleResponseDto updateArticle(String articleId, ArticleDto updatedArticleDto) {
        ObjectId objectId = new ObjectId(articleId);
        Optional<Article> optionalArticle = articleRepository.findById(objectId);
        if (optionalArticle.isPresent()) {
            Article existingArticle = optionalArticle.get();

            if (updatedArticleDto.getTitle() != null) {
                existingArticle.setTitle(updatedArticleDto.getTitle());
            }
            if (updatedArticleDto.getText() != null) {
                existingArticle.setText(updatedArticleDto.getText());
            }
            if (updatedArticleDto.getTags() != null) {
                existingArticle.setTags(updatedArticleDto.getTags());
            }
            if (updatedArticleDto.getMediaId() != null) {
                List<Media> mediaList = new ArrayList<>();

                Media media = new Media();
                media.setMediaType(mediaType.valueOf(updatedArticleDto.getMediaId()));
                mediaList.add(media);

                existingArticle.setMedias(mediaList);
            }

            Article updatedArticle = articleRepository.save(existingArticle);

            return modelMapper.map(updatedArticle, ArticleResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Article not found");
        }
    }

}
