package com.tripfestival.request;

import lombok.Getter;

@Getter
public class EventProcessRequest {
    private String name;
    private String description;
    private String address;
    private int visitor;
    private boolean inout;
    private Long worldCountryCityId;
    private Long eventCategoryId;
    private Long eventSeasonId;
}
