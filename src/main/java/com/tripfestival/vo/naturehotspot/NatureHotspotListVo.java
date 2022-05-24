package com.tripfestival.vo.naturehotspot;

import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.vo.landmark.LandmarkListVo;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public class NatureHotspotListVo {

    private List<NatureHotspotVo> items = null;

    public NatureHotspotListVo(List<NatureHotspot> natureHotspotList, List<List<LandmarkHashTag>> landmarkHashTagListVoList) {
        AtomicInteger index = new AtomicInteger();

        items = natureHotspotList.stream()
                .map(natureHotspot -> new NatureHotspotVo(natureHotspot, landmarkHashTagListVoList.get(index.getAndIncrement())))
                .collect(Collectors.toList());
    }

    @Getter
    class NatureHotspotVo {
        private Long id;
        private Long landmarkId;
        private String landmarkName;
        private String img;
        private List<HashTagVo> items = null;

        public NatureHotspotVo(NatureHotspot natureHotspot, List<LandmarkHashTag> landmarkHashTagList) {
            this.id = natureHotspot.getId();
            this.landmarkId = natureHotspot.getLandmark().getId();
            this.landmarkName = natureHotspot.getLandmark().getName();
            this.img = natureHotspot.getImg();
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
