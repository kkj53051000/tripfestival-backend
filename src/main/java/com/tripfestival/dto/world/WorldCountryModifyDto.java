package com.tripfestival.dto.world;

import com.tripfestival.request.world.WorldCountryModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorldCountryModifyDto {
    private Long worldCountryId;
    private String name;
    private String currency;
    private String capital;
    private String exchangeRatio;

    public WorldCountryModifyDto(Long worldCountryId, WorldCountryModifyRequest req) {
        this.worldCountryId = worldCountryId;
        this.name = req.getName();
        this.currency = req.getCurrency();
        this.capital = req.getCapital();
        this.exchangeRatio = req.getExchangeRatio();
    }
}
