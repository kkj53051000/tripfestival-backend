package com.tripfestival.request.landmark;

import lombok.Getter;

@Getter
public class LandmarkTimeProcessRequest {
    private String title;
    private String startTime;
    private String endTime;
    private Long landmarkId;
}
