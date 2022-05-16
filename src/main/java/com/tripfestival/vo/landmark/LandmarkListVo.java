package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTagVo;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkListVo {

    private List<LandmarkVo> items = null;

    public LandmarkListVo(List<Landmark> landmarkList, List<LandmarkHashTagListVo> landmarkHashTagListVoList) {
        this.items = landmarkList.stream()
                .map(landmark -> new LandmarkVo(landmark))
                .collect(Collectors.toList());

        this.items = landmarkHashTagListVoList.stream()
                .map(landmarkHashTagListVo -> new LandmarkVo(landmarkHashTagListVo))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkVo {
        private String name;
        private String img;
        private List<LandmarkHashTagVo> items = null;

        public LandmarkVo(Landmark landmark) {
            this.name = landmark.getName();
            this.img = landmark.getImg();
        }

        public LandmarkVo(LandmarkHashTagListVo landmarkHashTagListVo) {
            this.items = landmarkHashTagListVo.getItems();
        }
    }


}
