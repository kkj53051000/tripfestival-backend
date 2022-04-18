package com.tripfestival.request;

import lombok.Data;

@Data
public class LandmarkProcessRequest {
    private String name;
    private String description;
    private String address;
    private String homepage;
    private Long worldCountryCityId;
}
