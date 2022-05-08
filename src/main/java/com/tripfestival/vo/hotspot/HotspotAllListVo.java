package com.tripfestival.vo.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotspotAllListVo {

    private List<HotspotVo> items = null;

    public HotspotAllListVo(List<Hotspot> hotspotList) {
        this.items = hotspotList.stream()
                .map(hotspot -> new HotspotVo(hotspot))
                .collect(Collectors.toList());
    }

    @Getter
    class HotspotVo {
        private Long id;
        private String hotspotTypeName;
        private Long landmarkId;
        private String landmarkName;

        public HotspotVo(Hotspot hotspot) {
            this.id = hotspot.getId();
            this.hotspotTypeName = hotspot.getHotspotType().getName();
            this.landmarkId = hotspot.getLandmark().getId();
            this.landmarkName = hotspot.getLandmark().getName();
        }
    }
}
