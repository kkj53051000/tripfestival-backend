package com.tripfestival.dto.landmark;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class LandmarkImgProcessDto {
    private List<MultipartFile> files;
    private Long landmarkId;

    public LandmarkImgProcessDto(List<MultipartFile> files, Long landmarkId) {
        this.files = files;
        this.landmarkId = landmarkId;
    }
}
