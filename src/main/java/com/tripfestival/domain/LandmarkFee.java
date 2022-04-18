package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "LANDMARKFEE_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "LANDMARKFEE_SEQ", allocationSize = 10)
public class LandmarkFee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDMARKFEE_SEQ_GENERATOR")
    @Column(name = "landmarkfee_id")
    private Long id;

    private String title;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    public LandmarkFee(String title, int price, Landmark landmark) {
        this.title = title;
        this.price = price;
        this.landmark = landmark;
    }
}
