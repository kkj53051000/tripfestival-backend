package com.tripfestival.request.landmark;

import lombok.Getter;

@Getter
public class LandmarkFeeProcessRequest {
    private String title;
    private int price;
    private Long landmarkId;
}