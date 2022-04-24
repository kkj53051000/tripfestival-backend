package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventModifyDto {
    private Long eventId;
    private String name;
    private String description;
    private String address;
    private Integer visitor;
    private Boolean inout;
    private Long worldCountryCityId;
    private Long eventCategoryId;
    private Long eventSeasonId;
}
