package com.tripfestival.domain.event;

import com.tripfestival.domain.world.WorldCountryCityRegion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(
        name = "EVENT_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENT_SEQ", allocationSize = 10)
public class Event {  // 축제
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENT_SEQ_GENERATOR")
    @Column(name = "event_id")
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String img;
    private String description;
    private String address;
    private Integer visitor;
    @Column(name = "in_out")
    private Boolean inout;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worldcountrycityregion_id")
    private WorldCountryCityRegion worldCountryCityRegion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventcategory_id")
    private EventCategory eventCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventseason_id")
    private EventSeason eventSeason;
}
