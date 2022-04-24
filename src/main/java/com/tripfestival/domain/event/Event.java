package com.tripfestival.domain.event;

import com.tripfestival.domain.world.WorldCountryCity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(
        name = "EVENT_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENT_SEQ", allocationSize = 10)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENT_SEQ_GENERATOR")
    @Column(name = "event_id")
    private Long id;

    private String name;
    private String description;
    private String address;
    private Integer visitor;
    @Column(name = "in_out")
    private Boolean inout;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordcountrycity_id")
    private WorldCountryCity worldCountryCity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventcategory_id")
    private EventCategory eventCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventseason_id")
    private EventSeason eventSeason;

    public Event(String name, String description, String address, Integer visitor, Boolean inout, WorldCountryCity worldCountryCity, EventCategory eventCategory, EventSeason eventSeason) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.visitor = visitor;
        this.inout = inout;
        this.worldCountryCity = worldCountryCity;
        this.eventCategory = eventCategory;
        this.eventSeason = eventSeason;
    }
}