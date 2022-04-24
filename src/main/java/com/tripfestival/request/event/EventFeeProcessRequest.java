package com.tripfestival.request.event;

import lombok.Getter;

@Getter
public class EventFeeProcessRequest {
    private String title;
    private int price;
    private Long eventId;
}
