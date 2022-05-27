package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventFeeModifyRequest {
    private String title;
    private Integer price;
}
