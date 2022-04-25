package com.tripfestival.vo.world;

import com.tripfestival.domain.world.WorldCountryCity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WorldCountryCityNameListVo {

    List<WorldCountryCityNameVo> items = null;

    public WorldCountryCityNameListVo(List<WorldCountryCity> worldCountryCityList) {
        this.items = worldCountryCityList.stream()
                .map(worldCountryCity -> new WorldCountryCityNameVo(worldCountryCity))
                .collect(Collectors.toList());
    }

    @Getter
    class WorldCountryCityNameVo {
        private String name;

        public WorldCountryCityNameVo(WorldCountryCity worldCountryCity) {
            this.name  = worldCountryCity.getName();
        }
    }
}
