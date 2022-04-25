package com.tripfestival.request.landmark;

import lombok.Getter;

@Getter
public class LandmarkReviewProcessRequest {
    private String content;
    private Byte score;
    private Long landmarkId;
}
