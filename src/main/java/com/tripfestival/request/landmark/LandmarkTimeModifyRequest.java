package com.tripfestival.request.landmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkTimeModifyRequest {
    private String title;
    private String startTime;
    private String endTime;
}
