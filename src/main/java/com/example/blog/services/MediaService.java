package com.example.blog.services;

import com.example.blog.dtos.media.MediaDto;
import com.example.blog.dtos.media.MediaResponseDto;
import com.example.blog.dtos.media.MediaDto;
import com.example.blog.dtos.media.MediaResponseDto;
import com.example.blog.entities.Media;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaService {
    MediaResponseDto add(MediaDto mediaDto);
    List<MediaResponseDto> getAll(Pageable pageable);
    boolean deleteMediaById(String mediaId);

}
