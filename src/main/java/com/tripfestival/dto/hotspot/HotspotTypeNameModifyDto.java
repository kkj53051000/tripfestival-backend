package com.tripfestival.dto.hotspot;

import com.tripfestival.request.hotspot.HotspotTypeNameModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotspotTypeNameModifyDto {
    private Long hotspotTypeId;
    private String name;

    public HotspotTypeNameModifyDto(Long hotspotTypeId, HotspotTypeNameModifyRequest req) {
        this.hotspotTypeId = hotspotTypeId;
        this.name = req.getName();
    }
}
