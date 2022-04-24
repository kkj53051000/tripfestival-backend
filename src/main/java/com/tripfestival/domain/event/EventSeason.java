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
public class EventSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eventseason_id")
    private Long id;

    private String name;
    private String img;

    public EventSeason(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
