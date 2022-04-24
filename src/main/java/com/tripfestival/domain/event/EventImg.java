package com.tripfestival.domain.event;

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
        name = "EVENTIMG_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTIMG_SEQ", allocationSize = 50)
public class EventImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTIMG_SEQ_GENERATOR")
    @Column(name = "eventimg_id")
    private Long id;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public EventImg(String img, Event event) {
        this.img = img;
        this.event = event;
    }
}
