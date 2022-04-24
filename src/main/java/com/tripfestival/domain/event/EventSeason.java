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
        name = "EVENTSEASON_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTSEASON_SEQ", allocationSize = 10)
public class EventSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTSEASON_SEQ_GENERATOR")
    @Column(name = "eventseason_id")
    private Long id;

    private String name;
    private String img;

    public EventSeason(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
