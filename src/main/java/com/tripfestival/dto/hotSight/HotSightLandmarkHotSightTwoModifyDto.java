package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightLandmarkHotSightTwoModifyRequest;
import lombok.Getter;

@Getter
public class HotSightLandmarkHotSightTwoModifyDto {
    private Long hotSightLandmarkId;
    private Long hotSightTwoId;

    public HotSightLandmarkHotSightTwoModifyDto(Long hotSightLandmarkId, HotSightLandmarkHotSightTwoModifyRequest req) {
        this.hotSightLandmarkId = hotSightLandmarkId;
        this.hotSightTwoId = req.getHotSightTowId();
    }
}
