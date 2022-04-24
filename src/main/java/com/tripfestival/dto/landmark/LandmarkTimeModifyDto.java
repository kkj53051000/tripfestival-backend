package com.tripfestival.dto.landmark;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LandmarkTimeModifyDto {
    private Long landmarkTimeId;
    private String title;
    private String startTime;
    private String endTime;
}
