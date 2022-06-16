package com.tripfestival.dto.naturehotspot;

import com.tripfestival.request.naturehotspot.NatureHotspotNatureHotspotTypeModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NatureHotspotNatureHotspotTypeModifyDto {
    private Long natureHotspotId;
    private Long natureHotspotTypeId;

    public NatureHotspotNatureHotspotTypeModifyDto(Long natureHotspotId, NatureHotspotNatureHotspotTypeModifyRequest req) {
        this.natureHotspotId = natureHotspotId;
        this.natureHotspotTypeId = req.getNatureHotspotTypeId();
    }
}
