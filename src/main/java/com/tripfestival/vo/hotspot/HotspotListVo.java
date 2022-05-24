package com.tripfestival.vo.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotspotListVo {

    List<HotspotVo> items = null;

    public HotspotListVo(List<Hotspot> hotspotList) {
        this.items = hotspotList.stream()
                .map(hotspot -> new HotspotVo(hotspot))
                .collect(Collectors.toList());
    }

    @Getter
    class HotspotVo {
        private Long id;
        private Long landmarkId;
        private String landmarkName;
        private String landmarkImg;

        public HotspotVo(Hotspot hotspot) {
            this.id = hotspot.getId();
            this.landmarkId = hotspot.getLandmark().getId();
            this.landmarkName = hotspot.getLandmark().getName();
            this.landmarkImg = hotspot.getLandmark().getImg();
        }
    }
}
