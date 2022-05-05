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

    @Getter
    class WorldCountryNameVo {
        private Long id;
        private String name;
        private String currency;
        private String capital;
        private String exchangeRatio;

        public WorldCountryNameVo(WorldCountry worldCountry) {
            this.id = worldCountry.getId();
            this.name = worldCountry.getName();
            this.currency = worldCountry.getCurrency();
            this.capital = worldCountry.getCapital();
            this.exchangeRatio = worldCountry.getExchangeRatio();
        }
    }
}
