package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "EVENTTIME_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTTIME_SEQ", allocationSize = 10)
public class EventTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTTIME_SEQ_GENERATOR")
    @Column(name = "eventtime_id")
    private Long id;

    private String title;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public EventTime(String title, String time, Event event) {
        this.title = title;
        this.time = time;
        this.event = event;
    }
}
