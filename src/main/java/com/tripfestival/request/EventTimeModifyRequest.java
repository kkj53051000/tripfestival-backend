package com.tripfestival.request;

import lombok.Getter;

@Getter
public class EventTimeModifyRequest {
    private String title;
    private String startTime;
    private String endTime;
}
