package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "EVENTFEE_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTFEE_SEQ", allocationSize = 10)
public class EventFee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTFEE_SEQ_GENERATOR")
    @Column(name = "eventfee_id")
    private Long id;

    private String title;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public EventFee(String title, int price, Event event) {
        this.title = title;
        this.price = price;
        this.event = event;
    }
}
