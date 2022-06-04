package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkVo {
    private Long id;
    private String name;
    private String img;
    private String description;
    private String address;
    private String homepage;
    private LandmarkImgListVo imgList;

    public LandmarkVo(Landmark landmark, List<LandmarkImg> landmarkImgList) {
        this.id = landmark.getId();
        this.name = landmark.getName();
        this.img = landmark.getImg();
        this.description = landmark.getDescription();
        this.address = landmark.getAddress();
        this.homepage = landmark.getHomepage();
        this.imgList = new LandmarkImgListVo(landmarkImgList);
    }
}
