package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "EVENTREVIEWIMG_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTREVIEWIMG_SEQ", allocationSize = 50)
public class EventReviewImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTREVIEWIMG_SEQ_GENERATOR")
    @Column(name = "eventreviewimg_id")
    private Long id;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventreview_id")
    private EventReview eventReview;

    public EventReviewImg(String img, EventReview eventReview) {
        this.img = img;
        this.eventReview = eventReview;
    }
}
