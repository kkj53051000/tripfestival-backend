package com.tripfestival.vo.event;

import com.tripfestival.domain.event.EventFee;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventFeeAllListVo {

    List<EventFeeVo> items = null;

    public EventFeeAllListVo(List<EventFee> eventFeeList) {
        items = eventFeeList.stream()
                .map(eventFee -> new EventFeeVo(eventFee))
                .collect(Collectors.toList());
    }

    @Getter
    class EventFeeVo {
        private String title;
        private int price;
        private Long eventId;
        private String eventName;

        public EventFeeVo(EventFee eventFee) {
            this.title = eventFee.getTitle();
            this.price = eventFee.getPrice();
            this.eventId = eventFee.getEvent().getId();
            this.eventName = eventFee.getEvent().getName();
        }
    }
}
