package com.tripfestival.request.landmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkTimeProcessRequest {
    private String title;
    private String startTime;
    private String endTime;
    private Long landmarkId;
}
