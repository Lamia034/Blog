package com.example.blog.services.impl;

import com.example.blog.dtos.article.ArticleDto;
import com.example.blog.dtos.article.ArticleResponseDto;
import com.example.blog.dtos.media.MediaDto;
import com.example.blog.dtos.media.MediaResponseDto;
import com.example.blog.entities.Article;
import com.example.blog.entities.Media;
import com.example.blog.entities.Media;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.repositories.ArticleRepository;
import com.example.blog.repositories.MediaRepository;
import com.example.blog.services.ArticleService;
import com.example.blog.services.MediaService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;


    public MediaServiceImpl(MediaRepository mediaRepository, ModelMapper modelMapper) {
        this.mediaRepository = mediaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MediaResponseDto add(MediaDto mediaDto) {
        try {
            Media media = modelMapper.map(mediaDto, Media.class);
            Media savedMedia = mediaRepository.save(media);
            return modelMapper.map(savedMedia, MediaResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save media: " + e.getMessage());
        }
    }


    @Override
    @Transactional
    public List<MediaResponseDto> getAll(Pageable pageable) {
        try {
            Page<Media> mediaPage = mediaRepository.findAll(pageable);
            return mediaPage.stream()
                    .map(media -> modelMapper.map(media, MediaResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve medias: " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public boolean deleteMediaById(String mediaId) {
        try {
            ObjectId objectId = new ObjectId(mediaId);
            Optional<Media> mediaOptional = mediaRepository.findById(objectId);
            if (mediaOptional.isPresent()) {
                mediaRepository.delete(mediaOptional.get());
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid media ID format", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete media: " + e.getMessage());
        }
    }


}
