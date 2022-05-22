package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.Landmark;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LandmarkVo {
    private Long id;
    private String name;
    private String img;
    private String description;
    private String address;
    private String homepage;

    public LandmarkVo(Landmark landmark) {
        this.id = landmark.getId();
        this.name = landmark.getName();
        this.img = landmark.getImg();
        this.description = landmark.getDescription();
        this.address = landmark.getAddress();
        this.homepage = landmark.getHomepage();
    }
}
