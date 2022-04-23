package com.tripfestival.domain;

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
        name = "HOTSPOT_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "HOTSPOT_SEQ", allocationSize = 10)
public class Hotspot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HOTSPOT_SEQ_GENERATOR")
    @Column(name = "hotspot_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotspottype_id")
    private HotspotType hotspotType;

    public Hotspot(Landmark landmark, HotspotType hotspotType) {
        this.landmark = landmark;
        this.hotspotType = hotspotType;
    }
}
