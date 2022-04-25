package com.tripfestival.dto.landmark;

import com.tripfestival.request.landmark.LandmarkModifyRequest;
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
    private Long worldCountryCityRegionId;

    public LandmarkModifyDto(Long landmarkId, LandmarkModifyRequest req) {
        this.landmarkId = landmarkId;
        this.name = req.getName();
        this.description = req.getDescription();
        this.address = req.getAddress();
        this.homepage = req.getHomepage();
        this.worldCountryCityRegionId = req.getWorldCountryCityRegionId();
    }
}
