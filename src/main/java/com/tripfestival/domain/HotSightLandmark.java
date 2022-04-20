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
        name = "HOTSIGHTLANDMARK_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "HOTSIGHTLANDMARK_SEQ", allocationSize = 10)
public class HotSightLandmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HOTSIGHTLANDMARK_SEQ_GENERATOR")
    @Column(name = "hotsightlandmark_id")
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotsighttwo_id")
    private HotSightTwo hotSightTwo;

    public HotSightLandmark(String description, Landmark landmark, HotSightTwo hotSightTwo) {
        this.description = description;
        this.landmark = landmark;
        this.hotSightTwo = hotSightTwo;
    }
}
