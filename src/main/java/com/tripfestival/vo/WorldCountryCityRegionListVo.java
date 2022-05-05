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

    @Getter
    class WorldCountryCityRegionVo {
        private Long id;
        private String name;

        public WorldCountryCityRegionVo(WorldCountryCityRegion worldCountryCityRegion) {
            this.id = worldCountryCityRegion.getId();
            this.name = worldCountryCityRegion.getName();
        }
    }
}
