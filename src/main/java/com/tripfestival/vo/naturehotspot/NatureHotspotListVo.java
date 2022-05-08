package com.tripfestival.vo.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspot;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NatureHotspotListVo {

    private List<NatureHotspotVo> items = null;

    public NatureHotspotListVo(List<NatureHotspot> natureHotspotList) {
        items = natureHotspotList.stream()
                .map(natureHotspot -> new NatureHotspotVo(natureHotspot))
                .collect(Collectors.toList());
    }

    @Getter
    class NatureHotspotVo {
        private Long id;
        private String landmarkName;
        private String img;

        public NatureHotspotVo(NatureHotspot natureHotspot) {
            this.id = natureHotspot.getId();
            this.landmarkName = natureHotspot.getLandmark().getName();
            this.img = natureHotspot.getImg();
        }
    }
}
