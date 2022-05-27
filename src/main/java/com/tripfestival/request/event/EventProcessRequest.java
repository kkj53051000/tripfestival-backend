package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventProcessRequest {
    private String name;
    private String startDate;
    private String endDate;
    private String description;
    private String address;
    private int visitor;
    private int inout;
    private Long worldCountryCityRegionId;
    private Long eventCategoryId;
    private Long eventSeasonId;
}
