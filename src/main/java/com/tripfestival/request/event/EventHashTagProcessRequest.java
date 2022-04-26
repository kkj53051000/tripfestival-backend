package com.tripfestival.request.event;

import lombok.Getter;

@Getter
public class EventHashTagProcessRequest {
    private String name;
    private Long eventId;
}
