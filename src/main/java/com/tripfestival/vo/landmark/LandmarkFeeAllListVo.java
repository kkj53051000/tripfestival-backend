package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkFee;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkFeeAllListVo {

    private List<LandmarkFeeVo> items = null;

    public LandmarkFeeAllListVo(List<LandmarkFee> landmarkFeeList) {
        this.items = landmarkFeeList.stream()
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
