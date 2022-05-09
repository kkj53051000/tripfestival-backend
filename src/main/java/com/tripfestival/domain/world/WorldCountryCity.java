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
public class WorldCountryCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "worldcountrycity_id")
    private Long id;

    private String name;
    private String cityImg;
    private Integer areaCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worldcountry_id")
    private WorldCountry worldCountry;
}
