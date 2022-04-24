package com.tripfestival.vo;

import com.tripfestival.domain.event.EventImg;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EventImgListVo {

    List<EventImgVo> items = null;

    public EventImgListVo(List<EventImg> eventImgList) {
        items = eventImgList.stream()
                .map(eventImg -> new EventImgVo(eventImg))
                .collect(Collectors.toList());
    }

    class EventImgVo {
        private String img;

        public EventImgVo(EventImg eventImg) {
            this.img = eventImg.getImg();
        }
    }
}
