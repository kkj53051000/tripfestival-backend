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
        name = "LANDMARKREVIEWIMG_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "LANDMARKREVIEWIMG_SEQ", allocationSize = 50)
public class LandmarkReviewImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDMARKREVIEWIMG_SEQ_GENERATOR")
    @Column(name = "landmarkreviewimg_id")
    private Long id;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmarkreview_id")
    private LandmarkReview landmarkReview;

    public LandmarkReviewImg(String img, LandmarkReview landmarkReview) {
        this.img = img;
        this.landmarkReview = landmarkReview;
    }
}
