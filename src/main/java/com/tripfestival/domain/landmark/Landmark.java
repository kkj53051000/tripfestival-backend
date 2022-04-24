package com.tripfestival.domain.landmark;

import com.tripfestival.domain.world.WorldCountryCity;
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
        name = "LANDMARK_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "LANDMARK_SEQ", allocationSize = 10)
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDMARK_SEQ_GENERATOR")
    @Column(name = "landmark_id")
    private Long id;

    private String name;
    @Lob
    private String description;
    private String address;
    private String homepage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordcountrycity_id")
    private WorldCountryCity worldCountryCity;
}
