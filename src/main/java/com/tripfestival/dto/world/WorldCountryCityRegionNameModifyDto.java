package com.tripfestival.dto.world;

import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import lombok.Getter;

@Getter
public class WorldCountryCityRegionNameModifyDto {
    private Long worldCountryCityId;
    private String name;

    public WorldCountryCityRegionNameModifyDto(Long worldCountryCityId, WorldCountryCityRegionNameModifyRequest req) {
        this.worldCountryCityId = worldCountryCityId;
        this.name = req.getName();
    }
}
