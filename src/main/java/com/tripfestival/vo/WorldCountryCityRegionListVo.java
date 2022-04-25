package com.tripfestival.vo;

import com.tripfestival.domain.world.WorldCountryCityRegion;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WorldCountryCityRegionListVo {

    private List<WorldCountryCityRegionVo> items = null;

    public WorldCountryCityRegionListVo(List<WorldCountryCityRegion> worldCountryCityRegionList) {
        items = worldCountryCityRegionList.stream()
                .map(worldCountryCityRegion -> new WorldCountryCityRegionVo(worldCountryCityRegion))
                .collect(Collectors.toList());
    }

    class WorldCountryCityRegionVo {
        private String name;

        public WorldCountryCityRegionVo(WorldCountryCityRegion worldCountryCityRegion) {
            this.name = worldCountryCityRegion.getName();
        }
    }
}
