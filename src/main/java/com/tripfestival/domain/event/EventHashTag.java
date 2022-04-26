package com.tripfestival.domain.event;

import lombok.Data;

import javax.persistence.*;

@Entity
public class EventHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eventhashtag_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
