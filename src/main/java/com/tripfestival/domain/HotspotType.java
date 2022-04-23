package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class HotspotType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotspottype_id")
    private Long id;

    private String name;
    private String img;

    public HotspotType(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
