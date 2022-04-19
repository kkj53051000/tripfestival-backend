package com.tripfestival.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class LandmarkReviewImgProcessDto {
    private List<MultipartFile> files;
    private Long landmarkReviewId;

    public LandmarkReviewImgProcessDto(List<MultipartFile> files, Long landmarkReviewId) {
        this.files = files;
        this.landmarkReviewId = landmarkReviewId;
    }
}
