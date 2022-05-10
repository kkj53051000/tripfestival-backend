package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.Landmark;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkListVo {

    private List<LandmarkVo> items = null;

    public LandmarkListVo(List<Landmark> landmarkList) {
        this.items = landmarkList.stream()
                .map(landmark -> new LandmarkVo(landmark))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkVo {
        private String name;
        private String img;

        public LandmarkVo(Landmark landmark) {
            this.name = landmark.getName();
            this.img = landmark.getImg();
        }
    }
}
