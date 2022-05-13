package com.tripfestival.vo.event;

import com.tripfestival.domain.event.Event;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventListVo {

    List<EventVo> items = null;

    public EventListVo(List<Event> eventList) {
        this.items = eventList.stream()
                .map(event -> new EventVo(event))
                .collect(Collectors.toList());
    }

    @Getter
    class EventVo {
        private Long id;
        private String name;
        private String img;

        public EventVo(Event event) {
            this.id = event.getId();
            this.name = event.getName();
            this.img = event.getImg();
        }
    }
}
