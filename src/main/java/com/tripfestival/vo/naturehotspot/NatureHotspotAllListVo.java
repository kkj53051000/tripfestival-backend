package com.tripfestival.vo.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspot;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NatureHotspotAllListVo {

    private List<NatureHotspotVo> items = null;

    public NatureHotspotAllListVo(List<NatureHotspot> natureHotspotList) {
        this.items = natureHotspotList.stream()
                .map(natureHotspot -> new NatureHotspotVo(natureHotspot))
                .collect(Collectors.toList());
    }

    @Getter
    class NatureHotspotVo {
        private Long id;
        private String landmarkName;
        private String natureHotspotTypeName;

        public NatureHotspotVo(NatureHotspot natureHotspot) {
            this.id = natureHotspot.getId();
            this.landmarkName = natureHotspot.getLandmark().getName();
            this.natureHotspotTypeName = natureHotspot.getNatureHotspotType().getName();
        }

    }
}
