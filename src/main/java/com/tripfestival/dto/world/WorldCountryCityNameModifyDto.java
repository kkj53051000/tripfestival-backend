package com.tripfestival.dto.world;

import com.tripfestival.request.world.WorldCountryCityNameModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorldCountryCityNameModifyDto {
    private Long worldCountryCityId;
    private String name;

    public WorldCountryCityNameModifyDto(Long worldCountryCityId, WorldCountryCityNameModifyRequest req) {
        this.worldCountryCityId = worldCountryCityId;
        this.name = req.getName();
    }
}
