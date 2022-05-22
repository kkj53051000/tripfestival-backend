package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventFeeProcessRequest {
    private String title;
    private int price;
    private Long eventId;
}
