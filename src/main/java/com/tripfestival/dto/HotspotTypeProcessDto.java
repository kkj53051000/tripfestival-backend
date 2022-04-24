package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class HotspotTypeProcessDto {
    private MultipartFile file;
    private String name;
}
