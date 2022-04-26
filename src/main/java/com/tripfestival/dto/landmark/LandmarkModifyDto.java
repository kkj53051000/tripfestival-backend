package com.tripfestival.dto.landmark;

import com.tripfestival.request.landmark.LandmarkModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
