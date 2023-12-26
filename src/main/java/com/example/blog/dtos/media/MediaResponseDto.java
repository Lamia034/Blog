package com.example.blog.dtos.media;

import com.example.blog.enumerations.mediaType;
import lombok.Data;

@Data
public class MediaResponseDto {
    private String id;
    private String url;
    private mediaType media;
}
