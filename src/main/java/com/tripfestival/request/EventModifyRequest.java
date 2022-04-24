package com.tripfestival.request;

import lombok.Getter;

@Getter
public class EventModifyRequest {
    private String name;
    private String description;
    private String address;
    private Integer visitor;
    private Boolean inout;
    private Long worldCountryCityId;
    private Long eventCategoryId;
    private Long eventSeasonId;
}
