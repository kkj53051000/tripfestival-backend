package com.tripfestival.request.event;

import lombok.Getter;

@Getter
public class EventFeeModifyRequest {
    private String title;
    private Integer price;
}
