package com.tripfestival.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableGenerator(
        name = "LANDMARKTIME_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "LANDMARKTIME_SEQ", allocationSize = 10)
public class LandmarkTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDMARKTIME_SEQ_GENERATOR")
    @Column(name = "landmarktime_id")
    private Long id;

    private String title;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;
}
