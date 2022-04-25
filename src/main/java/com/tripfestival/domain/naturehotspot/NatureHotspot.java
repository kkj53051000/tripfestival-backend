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
public class NatureHotspot {  // 자연 관광지
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "naturehotspot_id")
    private Long id;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "naturehotspottype_id")
    private NatureHotspotType natureHotspotType;
}
