package com.tripfestival.dto.world;

import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorldCountryCityRegionNameModifyDto {
    private Long worldCountryCityRegionId;
    private String name;

    public WorldCountryCityRegionNameModifyDto(Long worldCountryCityId, WorldCountryCityRegionNameModifyRequest req) {
        this.worldCountryCityRegionId = worldCountryCityId;
        this.name = req.getName();
    }
}
