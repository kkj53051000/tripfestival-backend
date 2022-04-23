package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class NatureHotspotType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "naturehotspottype_id")
    private Long id;

    private String name;
    private String img;

    public NatureHotspotType(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
