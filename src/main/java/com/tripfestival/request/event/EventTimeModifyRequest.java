package com.tripfestival.request.event;

import lombok.Getter;

@Getter
public class EventTimeModifyRequest {
    private String title;
    private String startTime;
    private String endTime;
}
