package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventSeasonNameModifyRequest {
    private String name;
}
