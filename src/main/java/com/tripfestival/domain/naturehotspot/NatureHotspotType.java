package com.tripfestival.domain.naturehotspot;

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
