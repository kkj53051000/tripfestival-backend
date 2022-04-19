package com.tripfestival.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class HotspotProcessDto {
    private MultipartFile file;
    private String name;
    private Long worldCountryCityId;

    public HotspotProcessDto(MultipartFile file, String name, Long worldCountryCityId) {
        this.file = file;
        this.name = name;
        this.worldCountryCityId = worldCountryCityId;
    }
}
