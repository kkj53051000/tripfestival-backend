package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventTimeModifyRequest {
    private String title;
    private String startTime;
    private String endTime;
}
