package com.tripfestival.dto.landmark;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LandmarkListDto {
    private Long worldCountryCityRegionId;
    private Long worldCountryCityId;
}
