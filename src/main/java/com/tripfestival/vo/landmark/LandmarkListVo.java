package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public class LandmarkListVo {

    private List<LandmarkVo> items = null;

    public LandmarkListVo(List<Landmark> landmarkList, List<List<LandmarkHashTag>> landmarkHashTagListVoList) {
        AtomicInteger index = new AtomicInteger();

        this.items = landmarkList.stream()
                .map(landmark -> new LandmarkVo(landmark, landmarkHashTagListVoList.get(index.getAndIncrement())))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkVo {
        private Long id;
        private String name;
        private String img;
        private List<HashTagVo> items = null;

        public LandmarkVo(Landmark landmark, List<LandmarkHashTag> landmarkHashTagList) {
            this.id = landmark.getId();
            this.name = landmark.getName();
            this.img = landmark.getImg();
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
