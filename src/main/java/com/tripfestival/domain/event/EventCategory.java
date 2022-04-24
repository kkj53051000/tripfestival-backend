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
        name = "EVENTCATEGORY_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTCATEGORY_SEQ", allocationSize = 10)
public class EventCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTCATEGORY_SEQ_GENERATOR")
    @Column(name = "eventcategory_id")
    private Long id;

    private String name;
    private String img;

    public EventCategory(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
