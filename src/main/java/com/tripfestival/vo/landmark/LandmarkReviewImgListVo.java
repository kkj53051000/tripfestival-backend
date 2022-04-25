package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkReviewImg;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkReviewImgListVo {

    List<LandmarkReviewImgVo> items = null;

    public LandmarkReviewImgListVo(List<LandmarkReviewImg> landmarkReviewImgList) {
        items = landmarkReviewImgList.stream()
                .map(landmarkReviewImg -> new LandmarkReviewImgVo(landmarkReviewImg))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkReviewImgVo {
        private String img;

        public LandmarkReviewImgVo(LandmarkReviewImg landmarkReviewImg) {
            this.img = landmarkReviewImg.getImg();
        }
    }
}
