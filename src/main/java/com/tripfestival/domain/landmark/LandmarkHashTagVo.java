package com.tripfestival.domain.landmark;

import lombok.Getter;

@Getter
public class LandmarkHashTagVo {
    private Long id;
    private String name;

    public LandmarkHashTagVo(LandmarkHashTag landmarkHashTag) {
        this.id = landmarkHashTag.getId();
        this.name = landmarkHashTag.getName();
    }
}
