package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "HOTSPOT_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "HOTSPOT_SEQ", allocationSize = 10)
public class Hotspot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HOTSPOT_SEQ_GENERATOR")
    @Column(name = "hotspot_id")
    private Long id;

    private String name;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordcountrycity_id")
    private WorldCountryCity worldCountryCity;

    public Hotspot(String name, String img, WorldCountryCity worldCountryCity) {
        this.name = name;
        this.img = img;
        this.worldCountryCity = worldCountryCity;
    }
}
