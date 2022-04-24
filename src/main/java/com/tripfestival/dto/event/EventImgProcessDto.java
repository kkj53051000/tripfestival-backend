package com.tripfestival.dto.event;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class EventImgProcessDto {
    List<MultipartFile> files;
    Long eventId;

    public EventImgProcessDto(List<MultipartFile> files, Long eventId) {
        this.files = files;
        this.eventId = eventId;
    }
}
