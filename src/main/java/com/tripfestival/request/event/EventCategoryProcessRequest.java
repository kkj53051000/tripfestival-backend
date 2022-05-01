package com.tripfestival.request.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventCategoryProcessRequest {
    private String name;

    public EventCategoryProcessRequest(String name) {
        this.name = name;
    }
}
