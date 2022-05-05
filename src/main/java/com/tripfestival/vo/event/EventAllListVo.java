package com.tripfestival.vo.event;

import com.tripfestival.domain.event.Event;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventAllListVo {

    private List<EventVo> items = null;

    public EventAllListVo(List<Event> eventList) {
        this.items = eventList.stream()
                .map(event -> new EventVo(event))
                .collect(Collectors.toList());;
    }

    @Getter
    class EventVo {
        private Long id;
        private String name;
        private String description;
        private String address;
        private Integer visitor;
        private Boolean inout;
        private Long worldCountryCityRegionId;
        private Long eventCategoryId;
        private Long eventSeasonId;

        public EventVo(Event event) {
            this.id = event.getId();
            this.name = event.getName();
            this.description = event.getDescription();
            this.address = event.getAddress();
            this.visitor = event.getVisitor();
            this.inout = event.getInout();
            this.worldCountryCityRegionId = event.getWorldCountryCityRegion().getId();
            this.eventCategoryId = event.getEventCategory().getId();
            this.eventSeasonId = event.getEventSeason().getId();
        }
    }
}
