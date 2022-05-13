package com.tripfestival.dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventCategoryListDto {
    private Long eventCategoryId;
    private Long worldCountryCityId;
    private Long worldCountryCityRegionId;
}
