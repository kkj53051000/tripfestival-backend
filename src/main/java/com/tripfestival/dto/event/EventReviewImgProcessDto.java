package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@Data
@Builder
public class EventReviewImgProcessDto {
    private List<MultipartFile> files;
    private Long eventReviewId;

    public EventReviewImgProcessDto(List<MultipartFile> files, Long eventReviewId) {
        this.files = files;
        this.eventReviewId = eventReviewId;
    }
}
