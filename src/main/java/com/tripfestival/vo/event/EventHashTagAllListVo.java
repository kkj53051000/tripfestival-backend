package com.tripfestival.vo.event;

import com.tripfestival.domain.event.EventHashTag;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventHashTagAllListVo {

    List<EventHashTagVo> items = null;

    public EventHashTagAllListVo(List<EventHashTag> eventHashTagList) {
        this.items = eventHashTagList.stream()
                .map(eventHashTag -> new EventHashTagVo(eventHashTag))
                .collect(Collectors.toList());
    }

    @Getter
    class EventHashTagVo {
        private Long id;
        private String name;
        private Long eventId;
        private String eventName;

        public EventHashTagVo(EventHashTag eventHashTag) {
            this.id = eventHashTag.getId();
            this.name = eventHashTag.getName();
            this.eventId = eventHashTag.getEvent().getId();
            this.eventName = eventHashTag.getEvent().getName();
        }
    }
}
