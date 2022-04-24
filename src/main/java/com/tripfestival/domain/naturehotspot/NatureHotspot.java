package com.tripfestival.domain.naturehotspot;

import com.tripfestival.domain.landmark.Landmark;
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
        name = "NATUREHOTSPOT_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "NATUREHOTSPOT_SEQ", allocationSize = 10)
public class NatureHotspot {  // 자연관광지
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
