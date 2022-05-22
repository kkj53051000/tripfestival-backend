package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventModifyRequest {
    private String name;
    private String description;
    private String address;
    private Integer visitor;
    private Boolean inout;
    private Long worldCountryCityRegionId;
    private Long eventCategoryId;
    private Long eventSeasonId;
}
