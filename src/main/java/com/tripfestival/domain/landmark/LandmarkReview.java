package com.tripfestival.domain.landmark;

import com.tripfestival.domain.user.User;
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
        name = "LANDMARKREVIEW_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "LANDMARKREVIEW_SEQ", allocationSize = 50)
public class LandmarkReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDMARKREVIEW_SEQ_GENERATOR")
    @Column(name = "landmarkreview_id")
    private Long id;

    private String content;
    private Byte score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;
}
