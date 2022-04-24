package com.tripfestival.request.world;

import lombok.Getter;

@Getter
public class WorldCountryModifyRequest {
    private String name;
    private String currency;
    private String capital;
    private String exchangeRatio;
}
