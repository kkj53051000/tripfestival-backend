package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightLandmarkProcessRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class HotSightLandmarkProcessDto {
    private MultipartFile file;
    private Long landmarkId;
    private Long hotSightTwoId;

    public HotSightLandmarkProcessDto(MultipartFile file, HotSightLandmarkProcessRequest req) {
        this.file = file;
        this.landmarkId = req.getLandmarkId();
        this.hotSightTwoId = req.getHotSightTwoId();
    }
}
