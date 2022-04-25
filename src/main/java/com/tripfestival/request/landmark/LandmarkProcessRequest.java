package com.tripfestival.request.landmark;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
public class LandmarkProcessRequest {
    private String name;
    private String description;
    private String address;
    private String homepage;
    private Long worldCountryCityRegionId;

    public LandmarkProcessRequest(String name, String description, String address, String homepage, Long worldCountryCityRegionId) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.homepage = homepage;
        this.worldCountryCityRegionId = worldCountryCityRegionId;
    }
}
