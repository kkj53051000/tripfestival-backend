package com.tripfestival.dto.hotspot;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HotspotListDto {
    private Long hotspotTypeId;
    private Long worldCountryCityId;
    private Long worldCountryCityRegionId;
}
