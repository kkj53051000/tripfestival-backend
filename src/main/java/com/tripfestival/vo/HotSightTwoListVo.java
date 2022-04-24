package com.tripfestival.vo;

import com.tripfestival.domain.hotsight.HotSightTwo;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotSightTwoListVo {

    private List<HotSightTwoVo> items = null;

    public HotSightTwoListVo(List<HotSightTwo> hotSightTwoList) {
        items = hotSightTwoList.stream()
                .map(hotSightTwo -> new HotSightTwoVo(hotSightTwo))
                .collect(Collectors.toList());
    }

    @Getter
    class HotSightTwoVo {
        private Long id;
        private String name;
        private String img;

        public HotSightTwoVo(HotSightTwo hotSightTwo) {
            this.id = hotSightTwo.getId();
            this.name = hotSightTwo.getName();
            this.img = hotSightTwo.getImg();
        }
    }
}
