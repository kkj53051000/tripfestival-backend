package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LandmarkTimeModifyDto {
    private Long landmarkTimeId;
    private String title;
    private String time;
}
