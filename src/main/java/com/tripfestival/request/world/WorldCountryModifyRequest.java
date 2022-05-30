package com.tripfestival.request.world;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorldCountryModifyRequest {
    private String name;
    private String currency;
    private String capital;
    private String exchangeRatio;
}
