package com.tripfestival.dto.world;

import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import lombok.Getter;

@Getter
public class WorldCountryCityRegionNameModifyDto {
    private Long worldCountryCityRegionId;
    private String name;

    public WorldCountryCityRegionNameModifyDto(Long worldCountryCityId, WorldCountryCityRegionNameModifyRequest req) {
        this.worldCountryCityRegionId = worldCountryCityId;
        this.name = req.getName();
    }
}
