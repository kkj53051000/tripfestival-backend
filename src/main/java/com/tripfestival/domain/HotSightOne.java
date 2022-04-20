package com.tripfestival.domain;

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
        name = "HOTSIGHTONE_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "HOTSIGHTONE_SEQ", allocationSize = 10)
public class HotSightOne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HOTSIGHTONE_SEQ_GENERATOR")
    @Column(name = "hotsightone_id")
    private Long id;

    private String name;
    private String img;

    public HotSightOne(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
