package com.tripfestival.request.world;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorldCountryCityRegionProcessRequest {
    private String name;
    private Long worldCountryCityId;
}
