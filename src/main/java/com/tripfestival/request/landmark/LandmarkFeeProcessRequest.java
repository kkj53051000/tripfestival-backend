package com.tripfestival.request.landmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkFeeProcessRequest {
    private String title;
    private int price;
    private Long landmarkId;
}