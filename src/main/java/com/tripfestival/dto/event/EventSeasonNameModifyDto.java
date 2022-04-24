package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventSeasonNameModifyDto {
    private Long eventSeasonId;
    private String name;
}
