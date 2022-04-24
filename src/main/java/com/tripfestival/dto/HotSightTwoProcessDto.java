package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class HotSightTwoProcessDto {
    private MultipartFile file;
    private String name;
}
