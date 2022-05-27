package com.tripfestival.vo.landmark;

import lombok.Builder;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class LandmarkAllCountVo {
    private String landmarkAllCount;

    public LandmarkAllCountVo(Long landmarkAllCount) {
        DecimalFormat df = new DecimalFormat("###,###");
        this.landmarkAllCount = df.format(landmarkAllCount);
    }
}
