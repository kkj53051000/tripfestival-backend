package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkImg;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkImgListVo {

    private List<LandmarkImgVo> items;

    public LandmarkImgListVo(List<LandmarkImg> landmarkImgList) {
        items = landmarkImgList.stream()
                .map(landmarkImg -> new LandmarkImgVo(landmarkImg))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkImgVo {
        private String img;

        public LandmarkImgVo(LandmarkImg landmarkImg) {
            this.img = landmarkImg.getImg();
        }
    }
}
