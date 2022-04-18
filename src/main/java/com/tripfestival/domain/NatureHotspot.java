package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "NATUREHOTSPOT_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "NATUREHOTSPOT_SEQ", allocationSize = 10)
public class NatureHotspot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "NATUREHOTSPOT_SEQ_GENERATOR")
    @Column(name = "naturehotspot_id")
    private Long id;

    private String name;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordcountrycity_id")
    private WorldCountryCity worldCountryCity;

    public NatureHotspot(String name, String img, WorldCountryCity worldCountryCity) {
        this.name = name;
        this.img = img;
        this.worldCountryCity = worldCountryCity;
    }
}
