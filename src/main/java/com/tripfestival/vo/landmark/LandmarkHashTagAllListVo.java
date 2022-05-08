package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkHashTag;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkHashTagAllListVo {

    List<LandmarkHashTagVo> items = null;

    public LandmarkHashTagAllListVo(List<LandmarkHashTag> landmarkHashTagList) {
        this.items = landmarkHashTagList.stream()
                .map(landmarkHashTag -> new LandmarkHashTagVo(landmarkHashTag))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkHashTagVo {
        private Long id;
        private String name;
        private Long landmarkId;
        private String landmarkName;

        public LandmarkHashTagVo(LandmarkHashTag landmarkHashTag) {
            this.id = landmarkHashTag.getId();
            this.name = landmarkHashTag.getName();
            this.landmarkId = landmarkHashTag.getLandmark().getId();
            this.landmarkName = landmarkHashTag.getLandmark().getName();
        }
    }
}
