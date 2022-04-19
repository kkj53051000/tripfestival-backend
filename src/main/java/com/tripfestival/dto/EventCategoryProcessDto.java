package com.tripfestival.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class EventCategoryProcessDto {
    private MultipartFile file;
    private String name;

    public EventCategoryProcessDto(MultipartFile file, String name) {
        this.file = file;
        this.name = name;
    }
}
