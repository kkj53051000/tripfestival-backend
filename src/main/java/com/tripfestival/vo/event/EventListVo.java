package com.tripfestival.vo.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventHashTag;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public class EventListVo {

    List<EventVo> items = null;

    public EventListVo(List<Event> eventList, List<List<EventHashTag>> eventHashTagListList) {
        AtomicInteger index = new AtomicInteger();

        this.items = eventList.stream()
                .map(event -> new EventVo(event, eventHashTagListList.get(index.getAndIncrement())))
                .collect(Collectors.toList());
    }

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
        private List<HashTagVo> items = null;

        public EventVo(Event event, List<EventHashTag> eventHashTagList) {
            this.id = event.getId();
            this.name = event.getName();
            this.img = event.getImg();

            this.items = eventHashTagList.stream()
                    .map(eventHashTag -> new HashTagVo(eventHashTag.getName()))
                    .collect(Collectors.toList());
        }

        public EventVo(Event event) {
            this.id = event.getId();
            this.name = event.getName();
            this.img = event.getImg();
            this.items = null;
        }
    }

    @Getter
    class HashTagVo {
        private String name;

        public HashTagVo(String name) {
            this.name = name;
        }
    }
}
