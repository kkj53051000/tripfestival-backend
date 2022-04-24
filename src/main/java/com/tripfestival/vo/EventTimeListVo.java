package com.tripfestival.vo;

import com.tripfestival.domain.event.EventTime;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class EventTimeListVo {

    List<EventTimeVo> items = null;

    public EventTimeListVo(List<EventTime> eventTimeList) {
        items = eventTimeList.stream()
                .map(eventTime -> new EventTimeVo(eventTime))
                .collect(Collectors.toList());
    }

    @Getter
    class EventTimeVo {
        private String title;
        private String startDate;
        private String endDate;

        public EventTimeVo(EventTime eventTime) {
            this.title = eventTime.getTitle();
            this.startDate = eventTime.getStartTime().format(DateTimeFormatter.ofPattern("hh:mm", Locale.KOREA));
            this.endDate = eventTime.getEndTime().format(DateTimeFormatter.ofPattern("hh:mm", Locale.KOREA));
        }
    }
}
