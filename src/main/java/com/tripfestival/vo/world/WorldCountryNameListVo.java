package com.tripfestival.vo.world;

import com.tripfestival.domain.world.WorldCountry;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WorldCountryNameListVo {

    List<WorldCountryNameVo> items = null;

    public WorldCountryNameListVo(List<WorldCountry> worldCountryList) {
        this.items = worldCountryList.stream()
                .map(worldCountry -> new WorldCountryNameVo(worldCountry))
                .collect(Collectors.toList());
    }

    class WorldCountryNameVo {
        private String name;

        public WorldCountryNameVo(WorldCountry worldCountry) {
            this.name = worldCountry.getName();
        }
    }
}
