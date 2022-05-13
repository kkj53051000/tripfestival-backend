package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventSeasonListDto {
    private Long eventSeasonId;
    private Long worldCountryCityId;
    private Long worldCountryCityRegionId;
}
