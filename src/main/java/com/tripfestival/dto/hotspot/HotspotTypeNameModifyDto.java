package com.tripfestival.dto.hotspot;

import com.tripfestival.request.hotspot.HotspotTypeNameModifyRequest;
import lombok.Getter;

@Getter
public class HotspotTypeNameModifyDto {
    private Long hotspotTypeId;
    private String name;

    public HotspotTypeNameModifyDto(Long hotspotTypeId, HotspotTypeNameModifyRequest req) {
        this.hotspotTypeId = hotspotTypeId;
        this.name = req.getName();
    }
}
