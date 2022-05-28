package com.tripfestival.request.hotsight;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotSightLandmarkProcessRequest {
    private Long landmarkId;
    private Long hotSightTwoId;
}
