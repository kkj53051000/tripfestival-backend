package com.tripfestival.dto.landmark;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LandmarkModifyDto {
    private Long landmarkId;
    private String name;
    private String description;
    private String address;
    private String homepage;
    private Long worldCountryCityId;
}
