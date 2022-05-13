package com.tripfestival.vo.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventCategoryListVo {

    List<EventCategoryVo> items = null;

    public EventCategoryListVo(List<Event> eventList) {
        this.items = eventList.stream()
                .map(event -> new EventCategoryVo(event))
                .collect(Collectors.toList());
    }

    @Getter
    class EventCategoryVo {
        private Long id;
        private String name;

        public EventCategoryVo(Event event) {
            this.id = event.getId();
            this.name = event.getName();
        }
    }
}
