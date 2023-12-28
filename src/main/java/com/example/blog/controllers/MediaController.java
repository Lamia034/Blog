package com.example.blog.controllers;

import com.example.blog.dtos.media.MediaDto;
import com.example.blog.dtos.media.MediaResponseDto;
import com.example.blog.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medias")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<MediaResponseDto> saveMedia(@RequestBody MediaDto mediaDto) {
        MediaResponseDto savedMedia = mediaService.add(mediaDto);
        return new ResponseEntity<>(savedMedia, HttpStatus.CREATED);
    }

    @GetMapping
    public List<MediaResponseDto> getMedias(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mediaService.getAll(pageable);
    }


    @DeleteMapping("/{mediaId}")
    public ResponseEntity<String> deleteMedia(@PathVariable String mediaId) {
        boolean deleted = mediaService.deleteMediaById(mediaId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Media deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}