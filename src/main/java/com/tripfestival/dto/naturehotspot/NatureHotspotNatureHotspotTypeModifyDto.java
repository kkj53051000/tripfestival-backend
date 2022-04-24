package com.tripfestival.dto.naturehotspot;

import com.tripfestival.request.naturehotspot.NatureHotspotNatureHotspotTypeModifyRequest;
import lombok.Getter;

@Getter
public class NatureHotspotNatureHotspotTypeModifyDto {
    private Long natureHotspotId;
    private Long natureHotspotTypeId;

    public NatureHotspotNatureHotspotTypeModifyDto(Long natureHotspotId, NatureHotspotNatureHotspotTypeModifyRequest req) {
        this.natureHotspotId = natureHotspotId;
        this.natureHotspotTypeId = req.getNatureHotspotTypeId();
    }
}
