package com.tripfestival.request;

import lombok.Getter;

@Getter
public class EventTimeProcessRequest {
    private String title;
    private String time;
    private Long eventId;
}
