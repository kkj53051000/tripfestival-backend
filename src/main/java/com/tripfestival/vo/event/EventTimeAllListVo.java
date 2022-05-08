package com.tripfestival.vo.event;

import com.tripfestival.domain.event.EventTime;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventTimeAllListVo {

    private List<EventTimeVo> items = null;

    public EventTimeAllListVo(List<EventTime> eventTimeList) {
        this.items = eventTimeList.stream()
                .map(eventTime -> new EventTimeVo(eventTime))
                .collect(Collectors.toList());
    }

    @Getter
    class EventTimeVo {
        private Long id;
        private String title;
        private String startTime;
        private String endTime;
        private Long eventId;
        private String eventName;

        public EventTimeVo(EventTime eventTime) {
            this.id = eventTime.getId();
            this.title = eventTime.getTitle();
            this.startTime = String.valueOf(eventTime.getStartTime());
            this.endTime = String.valueOf(eventTime.getEndTime());
            this.eventId = eventTime.getEvent().getId();
            this.eventName = eventTime.getEvent().getName();
        }
    }
}
