package com.tripfestival.vo.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.vo.naturehotspot.NatureHotspotListVo;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public class HotspotListVo {

    List<HotspotVo> items = null;

    public HotspotListVo(List<Hotspot> hotspotList, List<List<LandmarkHashTag>> landmarkHashTagListVoList) {
        AtomicInteger index = new AtomicInteger();

        this.items = hotspotList.stream()
                .map(hotspot -> new HotspotVo(hotspot, landmarkHashTagListVoList.get(index.getAndIncrement())))
                .collect(Collectors.toList());
    }

    @Getter
    class HotspotVo {
        private Long id;
        private Long landmarkId;
        private String landmarkName;
        private String landmarkImg;
        private List<HashTagVo> items = null;

        public HotspotVo(Hotspot hotspot, List<LandmarkHashTag> landmarkHashTagList) {
            this.id = hotspot.getId();
            this.landmarkId = hotspot.getLandmark().getId();
            this.landmarkName = hotspot.getLandmark().getName();
            this.landmarkImg = hotspot.getLandmark().getImg();
            this.items = landmarkHashTagList.stream()
                    .map(landmarkHashTag -> new HashTagVo(landmarkHashTag.getName()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    class HashTagVo {
        private String name;

        public HashTagVo(String name) {
            this.name = name;
        }
    }
}
