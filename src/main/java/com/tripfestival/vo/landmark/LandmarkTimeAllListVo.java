package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkTime;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkTimeAllListVo {

    List<LandmarkTimeVo> items = null;

    public LandmarkTimeAllListVo(List<LandmarkTime> landmarkTimeList) {
        this.items = landmarkTimeList.stream()
                .map(landmarkTime -> new LandmarkTimeVo(landmarkTime))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkTimeVo {
        private Long id;
        private String title;
        private String startTime;
        private String endTime;

        public LandmarkTimeVo(LandmarkTime landmarkTime) {
            this.id = landmarkTime.getId();
            this.title = landmarkTime.getTitle();
            this.startTime = String.valueOf(landmarkTime.getStartTime());
            this.endTime = String.valueOf(landmarkTime.getEndTime());
        }
    }
}
