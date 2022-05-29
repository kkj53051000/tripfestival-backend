package com.tripfestival.request.landmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkHashTagProcessRequest {
    private String name;
    private Long landmarkId;
}
