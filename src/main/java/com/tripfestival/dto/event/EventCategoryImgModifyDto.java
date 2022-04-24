package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class EventCategoryImgModifyDto {
    private MultipartFile file;
    private Long eventCategoryId;

    public EventCategoryImgModifyDto(Long eventCategoryId, MultipartFile file) {
        this.file = file;
        this.eventCategoryId = eventCategoryId;
    }
}
