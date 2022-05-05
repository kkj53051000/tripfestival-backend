package com.tripfestival.request.event;

import lombok.Getter;

@Getter
public class EventProcessRequest {
    private String name;
    private String description;
    private String address;
    private int visitor;
    private int inout;
    private Long worldCountryCityRegionId;
    private Long eventCategoryId;
    private Long eventSeasonId;
}
