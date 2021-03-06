package com.tripfestival.dto.event;

import com.tripfestival.request.event.EventCategoryModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCategoryNameModifyDto {
    private Long eventCategoryId;
    private String name;

    public EventCategoryNameModifyDto(Long eventCategoryId, EventCategoryModifyRequest req) {
        this.eventCategoryId = eventCategoryId;
        this.name = req.getName();
    }
}
