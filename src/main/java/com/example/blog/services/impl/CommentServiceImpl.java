package com.example.blog.services.impl;

import com.example.blog.dtos.comment.CommentDto;
import com.example.blog.dtos.comment.CommentResponseDto;
import com.example.blog.entities.Article;
import com.example.blog.entities.Comment;
import com.example.blog.entities.User;
import com.example.blog.repositories.ArticleRepository;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

        private final ArticleRepository articleRepository;
        private final ModelMapper modelMapper;
        private final UserRepository userRepository;
        private final CommentRepository commentRepository;

        public CommentServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper, UserRepository userRepository, CommentRepository commentRepository1) {
            this.articleRepository = articleRepository;
            this.modelMapper = modelMapper;
            this.userRepository = userRepository;
            this.commentRepository = commentRepository1;
        }

        public CommentResponseDto save(CommentDto commentDto) {
            Comment comment = new Comment();

            comment.setUser(userRepository.findById(Long.valueOf(commentDto.getUserId())).get());
            comment.setArticle(articleRepository.findById(Long.valueOf(commentDto.getArticleId())).get());

            Article article = articleRepository.findById(Long.valueOf(comment.getArticle().getId()))
                    .orElseThrow(() -> new RuntimeException("No article found with ID: " + comment.getArticle().getId()));

            User user = userRepository.findById(Long.valueOf(comment.getUser().getId()))
                    .orElseThrow(() -> new RuntimeException("No user found with ID: " + comment.getUser().getId()));

            comment.setArticle(article);
            comment.setUser(user);
            comment.setText(commentDto.getText());


            Comment savedComment = commentRepository.save(comment);

            article.getComments().add(savedComment);

            Article savedArticle = articleRepository.save(article);

            return modelMapper.map(savedComment, CommentResponseDto.class);
        }


    }
