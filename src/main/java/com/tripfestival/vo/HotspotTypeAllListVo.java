package com.tripfestival.vo;

import com.tripfestival.domain.hotspot.HotspotType;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotspotTypeAllListVo {

    List<HotspotTypeVo> items = null;

    public HotspotTypeAllListVo(List<HotspotType> hotspotTypeList) {
        items = hotspotTypeList.stream()
                .map(hotspotType -> new HotspotTypeVo(hotspotType))
                .collect(Collectors.toList());
    }

    @Getter
    class HotspotTypeVo {
        private Long id;
        private String name;
        private String img;

        public HotspotTypeVo(HotspotType hotspotType) {
            this.id = hotspotType.getId();
            this.name = hotspotType.getName();
            this.img = hotspotType.getImg();
        }
    }
}
