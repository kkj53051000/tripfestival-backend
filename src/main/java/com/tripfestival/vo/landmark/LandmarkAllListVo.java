package com.tripfestival.vo.landmark;

import com.tripfestival.domain.landmark.Landmark;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LandmarkAllListVo {

    List<LandmarkVo> items = null;

    public LandmarkAllListVo(List<Landmark> landmarkList) {
        this.items = landmarkList.stream()
                .map(landmark -> new LandmarkVo(landmark))
                .collect(Collectors.toList());
    }

    @Getter
    class LandmarkVo {
        private Long id;
        private String name;
        private String img;
        private String description;
        private String address;
        private String homepage;
        private Long worldCountryCityRegionId;

        public LandmarkVo(Landmark landmark) {
            this.id = landmark.getId();
            this.name = landmark.getName();
            this.img = landmark.getImg();
            this.description = landmark.getDescription();
            this.address = landmark.getAddress();
            this.homepage = landmark.getHomepage();
            this.worldCountryCityRegionId = landmark.getWorldCountryCityRegion().getId();
        }
    }
}
