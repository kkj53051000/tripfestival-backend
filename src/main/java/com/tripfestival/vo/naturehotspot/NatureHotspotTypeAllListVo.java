package com.tripfestival.vo.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NatureHotspotTypeAllListVo {

    List<NatureHotspotTypeAllVo> items = null;

    public NatureHotspotTypeAllListVo(List<NatureHotspotType> natureHotspotTypeList) {
        this.items = natureHotspotTypeList.stream()
                .map(natureHotspotType -> new NatureHotspotTypeAllVo(natureHotspotType))
                .collect(Collectors.toList());
    }

    @Getter
    class NatureHotspotTypeAllVo {
        private Long id;
        private String name;

        public NatureHotspotTypeAllVo(NatureHotspotType natureHotspotType) {
            this.id = natureHotspotType.getId();
            this.name = natureHotspotType.getName();
        }
    }
}
