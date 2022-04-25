package com.tripfestival.vo.hotsight;

import com.tripfestival.domain.hotsight.HotSightLandmark;
import lombok.Getter;

import javax.persistence.Lob;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotSightLandmarkListVo {

    private List<HotSightLandmarkVo> items = null;

    public HotSightLandmarkListVo(List<HotSightLandmark> hotSightLandmarkList) {
        items = hotSightLandmarkList.stream()
                .map(hotSightLandmark -> new HotSightLandmarkVo(hotSightLandmark))
                .collect(Collectors.toList());
    }

    @Getter
    class HotSightLandmarkVo {
        private Long id;
        private String name;
        private String img;

        public HotSightLandmarkVo(HotSightLandmark hotSightLandmark) {
            this.id = hotSightLandmark.getId();
            this.name = hotSightLandmark.getLandmark().getName();
            this.img = hotSightLandmark.getImg();
        }
    }
}
