package com.tripfestival.request;

import lombok.Getter;

@Getter
public class EventTimeProcessRequest {
    private String title;
    private String startTime;
    private String endTime;
    private Long eventId;
}
