package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventFeeModifyDto {
    private Long eventFeeId;
    private String title;
    private Integer price;
}
