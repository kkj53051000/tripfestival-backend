package com.tripfestival.domain.landmark;

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
        name = "LANDMARKIMG_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "LANDMARKIMG_SEQ", allocationSize = 50)
public class LandmarkImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDMARKIMG_SEQ_GENERATOR")
    @Column(name = "landmarkimg_id")
    private Long id;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    public LandmarkImg(String img, Landmark landmark) {
        this.img = img;
        this.landmark = landmark;
    }
}
