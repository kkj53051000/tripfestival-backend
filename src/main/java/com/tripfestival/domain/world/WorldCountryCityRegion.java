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
public class WorldCountryCityRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wordcountrycityregion_id")
    private Long id;

    private String name;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordcountrycity_id")
    private WorldCountryCity worldCountryCity;

    public WorldCountryCityRegion(String name, String img, WorldCountryCity worldCountryCity) {
        this.name = name;
        this.img = img;
        this.worldCountryCity = worldCountryCity;
    }
}
