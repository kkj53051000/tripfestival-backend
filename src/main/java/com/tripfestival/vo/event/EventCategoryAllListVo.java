package com.tripfestival.vo.event;

import com.tripfestival.domain.event.EventCategory;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventCategoryAllListVo {

    List<EventCategoryVo> items = null;


    public EventCategoryAllListVo(List<EventCategory> eventCategoryList) {
        items = eventCategoryList.stream()
                .map(eventCategory -> new EventCategoryVo(eventCategory))
                .collect(Collectors.toList());
    }

    @Getter
    class EventCategoryVo {
        private Long id;
        private String name;
        private String img;

        public EventCategoryVo(EventCategory eventCategory) {
            this.id = eventCategory.getId();
            this.name = eventCategory.getName();
            this.img = eventCategory.getImg();
        }
    }
}
