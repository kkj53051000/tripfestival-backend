package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventSeasonNameModifyDto {
    private Long eventSeasonId;
    private String name;
}
