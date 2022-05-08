package com.tripfestival.vo.hotsight;

import com.tripfestival.domain.hotsight.HotSightLandmark;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotSightLandmarkAllListVo {

    List<HotSightLandmarkVo> items = null;

    public HotSightLandmarkAllListVo(List<HotSightLandmark> hotSightLandmarkList) {
        this.items = hotSightLandmarkList.stream()
                .map(hotSightLandmark -> new HotSightLandmarkVo(hotSightLandmark))
                .collect(Collectors.toList());
    }

    @Getter
    class HotSightLandmarkVo {
        private Long id;
        private String landmarkName;
        private String hotSightTwoName;

        public HotSightLandmarkVo(HotSightLandmark hotSightLandmark) {
            this.id = hotSightLandmark.getId();
            this.landmarkName = hotSightLandmark.getLandmark().getName();
            this.hotSightTwoName = hotSightLandmark.getHotSightTwo().getName();
        }
    }
}
