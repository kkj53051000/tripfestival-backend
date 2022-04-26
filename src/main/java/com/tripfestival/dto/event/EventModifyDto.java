package com.tripfestival.dto.event;

import com.tripfestival.request.event.EventModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventModifyDto {
    private Long eventId;
    private String name;
    private String description;
    private String address;
    private Integer visitor;
    private Boolean inout;
    private Long worldCountryCityRegionId;
    private Long eventCategoryId;
    private Long eventSeasonId;

    public EventModifyDto(Long eventId, EventModifyRequest req) {
        this.eventId = eventId;
        this.name = req.getName();
        this.description = req.getDescription();
        this.address = req.getAddress();
        this.visitor = req.getVisitor();
        this.inout = req.getInout();
        this.worldCountryCityRegionId = req.getWorldCountryCityRegionId();
        this.eventCategoryId = req.getEventCategoryId();
        this.eventSeasonId = req.getEventSeasonId();
    }
}
