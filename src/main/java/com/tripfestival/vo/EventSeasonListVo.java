package com.tripfestival.vo;

import com.tripfestival.domain.event.EventSeason;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventSeasonListVo {

    private List<EventSeasonVo> items = null;

    public EventSeasonListVo(List<EventSeason> eventSeasonList) {
        items = eventSeasonList.stream()
                .map(eventSeason -> new EventSeasonVo(eventSeason))
                .collect(Collectors.toList());
    }

    @Getter
    class EventSeasonVo {
        private Long id;
        private String name;
        private String img;

        public EventSeasonVo(EventSeason eventSeason) {
            this.id = eventSeason.getId();
            this.name = eventSeason.getName();
            this.img = eventSeason.getImg();
        }
    }
}
