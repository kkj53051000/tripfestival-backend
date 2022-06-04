package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.LandmarkHashTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LandmarkHashTagVo {
    private Long id;
    private String name;

    public LandmarkHashTagVo(LandmarkHashTag landmarkHashTag) {
        this.id = landmarkHashTag.getId();
        this.name = landmarkHashTag.getName();
    }
}
