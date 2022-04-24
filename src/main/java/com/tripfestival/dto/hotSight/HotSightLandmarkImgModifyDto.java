package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightLandmarkDescriptionModifyRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class HotSightLandmarkImgModifyDto {
    private Long hotSightLandmarkId;
    private MultipartFile file;

    public HotSightLandmarkImgModifyDto(Long hotSightLandmarkId, MultipartFile file) {
        this.hotSightLandmarkId = hotSightLandmarkId;
        this.file = file;
    }
}
