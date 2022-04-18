package com.tripfestival.request;

import lombok.Data;
import lombok.Getter;

@Getter
public class LandmarkFeeProcessRequest {
    private String title;
    private int price;
    private Long landmarkId;
}