package com.tripfestival.request.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventCategoryModifyRequest {
    private String name;

    public EventCategoryModifyRequest(String name) {
        this.name = name;
    }
}
