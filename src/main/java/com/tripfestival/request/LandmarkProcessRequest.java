package com.tripfestival.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LandmarkProcessRequest {
    private String name;
    private String description;
    private String address;
    private String homepage;
    private Long worldCountryCityId;

    public LandmarkProcessRequest(String name, String description, String address, String homepage, Long worldCountryCityId) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.homepage = homepage;
        this.worldCountryCityId = worldCountryCityId;
    }
}
