package com.tripfestival.domain.landmark;

import com.tripfestival.domain.world.WorldCountryCityRegion;
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
public class Landmark {  // 관광지
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "landmark_id")
    private Long id;

    private String name;
    private String img;
    @Lob
    private String description;
    private String address;
    private String homepage;
    private String contentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worldcountrycityregion_id")
    private WorldCountryCityRegion worldCountryCityRegion;
}
