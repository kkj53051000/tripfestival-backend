package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class EventHashTagProcessRequest {
    private String name;
    private Long eventId;

    public EventHashTagProcessRequest(String name, Long eventId) {
        this.name = name;
        this.eventId = eventId;
    }
}
