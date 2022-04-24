package com.tripfestival.vo;

import com.tripfestival.domain.hotsight.HotSightOne;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotSightOneListVo {

    List<HotSightOneVo> items = null;

    public HotSightOneListVo(List<HotSightOne> hotSightOneList) {
        items = hotSightOneList.stream()
                .map(hotSightOne -> new HotSightOneVo(hotSightOne))
                .collect(Collectors.toList());
    }

    @Getter
    class HotSightOneVo {
        private Long id;
        private String name;
        private String img;

        public HotSightOneVo(HotSightOne hotSightOne) {
            this.id = hotSightOne.getId();
            this.name = hotSightOne.getName();
            this.img = hotSightOne.getImg();
        }
    }
}
