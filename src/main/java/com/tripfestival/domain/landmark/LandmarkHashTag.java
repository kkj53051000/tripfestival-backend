package com.tripfestival.domain.landmark;

import javax.persistence.*;

@Entity
public class LandmarkHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "landmarkhashtag_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;
}
