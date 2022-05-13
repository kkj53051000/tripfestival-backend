package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventListDto {
    private Long worldCountryCityId;
    private Long worldCountryCityRegionId;
}
