package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LandmarkFeeModifyDto {
    private Long landmarkFeeId;
    private String title;
    private Integer price;
}
