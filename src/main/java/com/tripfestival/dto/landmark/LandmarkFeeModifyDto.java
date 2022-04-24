package com.tripfestival.dto.landmark;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LandmarkFeeModifyDto {
    private Long landmarkFeeId;
    private String title;
    private Integer price;
}
