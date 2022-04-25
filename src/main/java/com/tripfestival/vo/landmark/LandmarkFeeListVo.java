package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkFee;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkFeeListVo {

    List<LandmarkFeeVo> items = null;

    public LandmarkFeeListVo(List<LandmarkFee> landmarkFeeList) {
        items = landmarkFeeList.stream()
                .map(landmarkFee -> new LandmarkFeeVo(landmarkFee))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkFeeVo {
        private Long id;
        private String title;
        private int price;

        public LandmarkFeeVo(LandmarkFee landmarkFee) {
            this.id = landmarkFee.getId();
            this.title = landmarkFee.getTitle();
            this.price = landmarkFee.getPrice();
        }
    }
}
