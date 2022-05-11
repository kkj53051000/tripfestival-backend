package com.tripfestival.dto.naturehotspot;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NatureHotspotListDto {
    private Long natureHotspotTypeId;
    private Long worldCountryCityId;
    private Long worldCountryCityRegionId;
}
