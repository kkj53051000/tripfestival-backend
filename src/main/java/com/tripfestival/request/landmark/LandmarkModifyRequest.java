package com.tripfestival.request.landmark;

import lombok.Getter;

@Getter
public class LandmarkModifyRequest {
    private String name;
    private String description;
    private String address;
    private String homepage;
    private Long worldCountryCityId;
}
