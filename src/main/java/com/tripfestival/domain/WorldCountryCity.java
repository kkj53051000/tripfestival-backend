package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "WORLDCOUNTRYCITY_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "WORLDCOUNTRYCITY_SEQ", allocationSize = 10)
public class WorldCountryCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "WORLDCOUNTRYCITY_SEQ_GENERATOR")
    @Column(name = "wordcountrycity_id")
    private Long id;

    private String name;
    private String cityImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordcountry_id")
    private WorldCountry worldCountry;

    public WorldCountryCity(String name, String cityImg, WorldCountry worldCountry) {
        this.name = name;
        this.cityImg = cityImg;
        this.worldCountry = worldCountry;
    }
}
