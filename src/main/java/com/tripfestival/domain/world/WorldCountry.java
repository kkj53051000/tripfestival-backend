package com.tripfestival.domain.world;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableGenerator(
        name = "WORLDCOUNTRY_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "WORLDCOUNTRY_SEQ", allocationSize = 10)
public class WorldCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "WORLDCOUNTRY_SEQ_GENERATOR")
    @Column(name = "worldcountry_id")
    private Long id;

    private String name;
    private String flagImg;
    private String currency;
    private String capital;
    private String exchangeRatio;

    public WorldCountry(String name, String flagImg, String currency, String capital, String exchangeRatio) {
        this.name = name;
        this.flagImg = flagImg;
        this.currency = currency;
        this.capital = capital;
        this.exchangeRatio = exchangeRatio;
    }
}
