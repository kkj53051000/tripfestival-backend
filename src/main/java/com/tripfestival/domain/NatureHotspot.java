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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "naturehotspottype_id")
    private NatureHotspotType natureHotspotType;

    public NatureHotspot(Landmark landmark, NatureHotspotType natureHotspotType) {
        this.landmark = landmark;
        this.natureHotspotType = natureHotspotType;
    }
}
