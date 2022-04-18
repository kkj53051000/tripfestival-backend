package com.tripfestival.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class LandmarkImgProcessDto {
    private List<MultipartFile> files;
    private Long landmarkId;

    public LandmarkImgProcessDto(List<MultipartFile> files, Long landmarkId) {
        this.files = files;
        this.landmarkId = landmarkId;
    }
}
