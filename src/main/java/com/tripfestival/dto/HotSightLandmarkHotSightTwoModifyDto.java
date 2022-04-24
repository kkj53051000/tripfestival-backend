package com.tripfestival.dto;

import com.tripfestival.request.HotSightLandmarkHotSightTwoModifyRequest;
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
