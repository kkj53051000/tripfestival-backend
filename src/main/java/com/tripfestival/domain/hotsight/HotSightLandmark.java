package com.tripfestival.domain.hotsight;

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
        name = "HOTSIGHTLANDMARK_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "HOTSIGHTLANDMARK_SEQ", allocationSize = 10)
public class HotSightLandmark { // 특별한 관광지
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HOTSIGHTLANDMARK_SEQ_GENERATOR")
    @Column(name = "hotsightlandmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotsighttwo_id")
    private HotSightTwo hotSightTwo;
}
