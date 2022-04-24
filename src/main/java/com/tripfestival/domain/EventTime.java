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
        name = "EVENTTIME_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTTIME_SEQ", allocationSize = 10)
public class EventTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTTIME_SEQ_GENERATOR")
    @Column(name = "eventtime_id")
    private Long id;

    private String title;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
