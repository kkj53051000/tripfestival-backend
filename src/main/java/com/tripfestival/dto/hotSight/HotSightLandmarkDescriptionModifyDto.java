package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightLandmarkDescriptionModifyRequest;
import lombok.Getter;

@Getter
public class HotSightLandmarkDescriptionModifyDto {
    private Long hotSightLandmarkId;
    private String description;

    public HotSightLandmarkDescriptionModifyDto(Long hotSightLandmarkId, HotSightLandmarkDescriptionModifyRequest req) {
        this.hotSightLandmarkId = hotSightLandmarkId;
        this.description = req.getDescription();
    }
}
