package com.tripfestival.request.world;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorldCountryCityRegionNameModifyRequest {
    private String name;
}
