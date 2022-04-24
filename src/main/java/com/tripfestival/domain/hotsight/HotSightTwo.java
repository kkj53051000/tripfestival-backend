package com.tripfestival.domain.hotsight;

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
        name = "HOTSIGHTTWO_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "HOTSIGHTTWO_SEQ", allocationSize = 10)
public class HotSightTwo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HOTSIGHTTWO_SEQ_GENERATOR")
    @Column(name = "hotsighttwo_id")
    private Long id;

    private String name;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotsightone_id")
    private HotSightOne hotSightOne;

    public HotSightTwo(String name, String img, HotSightOne hotSightOne) {
        this.name = name;
        this.img = img;
        this.hotSightOne = hotSightOne;
    }
}
