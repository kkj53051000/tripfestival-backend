package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class EventSeasonImgModifyDto {
    private Long eventSeasonId;
    private MultipartFile file;
}
