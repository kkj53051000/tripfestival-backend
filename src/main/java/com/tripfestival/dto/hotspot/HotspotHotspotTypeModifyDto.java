package com.tripfestival.dto.hotspot;

import com.tripfestival.request.hotspot.HotspotHotspotTypeModifyRequest;
import lombok.Getter;

@Getter
public class HotspotHotspotTypeModifyDto {
    private Long hotspotId;
    private Long hotspotTypeId;

    public HotspotHotspotTypeModifyDto(Long hotspotId, HotspotHotspotTypeModifyRequest req) {
        this.hotspotId = hotspotId;
        this.hotspotTypeId = req.getHotspotTypeId();
    }
}
