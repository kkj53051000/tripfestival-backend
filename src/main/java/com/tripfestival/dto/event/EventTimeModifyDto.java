package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventTimeModifyDto {
    private Long eventTimeId;
    private String title;
    private String startTime;
    private String endTime;
}
