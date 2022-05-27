package com.tripfestival.vo.hotsight;

import com.tripfestival.domain.hotsight.HotSightLandmark;
import lombok.Getter;

import javax.persistence.Lob;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class HotSightLandmarkListVo {

    private List<HotSightLandmarkVo> items = null;

    public HotSightLandmarkListVo(List<HotSightLandmark> hotSightLandmarkList) {
        items = hotSightLandmarkList
                .stream()
                .map(hotSightLandmark -> new HotSightLandmarkVo(hotSightLandmark))
                .collect(Collectors.toList());

//        Stream<HotSightLandmark> HotSightLandmarkStream = hotSightLandmarkList.stream();
//        Stream<HotSightLandmarkVo> hotSightLandmarkVoStream = HotSightLandmarkStream.map(hotSightLandmark -> new HotSightLandmarkVo(hotSightLandmark));
//        List<HotSightLandmarkVo> collect = hotSightLandmarkVoStream.collect(Collectors.toList());
    }

    @Getter
    class HotSightLandmarkVo {
        private Long id;
        private Long landmarkId;
        private String name;
        private String img;

        public HotSightLandmarkVo(HotSightLandmark hotSightLandmark) {
            this.id = hotSightLandmark.getId();
            this.landmarkId = hotSightLandmark.getLandmark().getId();
            this.name = hotSightLandmark.getLandmark().getName();
            this.img = hotSightLandmark.getLandmark().getImg();
        }
    }
}
