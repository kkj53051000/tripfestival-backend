package com.tripfestival.vo.landmark;


import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.landmark.LandmarkHashTagVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class LandmarkHashTagListVo {

    List<LandmarkHashTagVo> items = null;

    public LandmarkHashTagListVo(List<LandmarkHashTag> landmarkHashTagList) {
        this.items = landmarkHashTagList.stream()
                .map(landmarkHashTag -> new LandmarkHashTagVo(landmarkHashTag))
                .collect(Collectors.toList());
    }

//    @Getter
//    class LandmarkHashTagVo {
//        private Long id;
//        private String name;
//
//        public LandmarkHashTagVo(LandmarkHashTag landmarkHashTag) {
//            this.id = landmarkHashTag.getId();
//            this.name = landmarkHashTag.getName();
//        }
//    }
}
