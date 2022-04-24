package com.tripfestival.domain.event;

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
        name = "EVENTREVIEW_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "EVENTREVIEW_SEQ", allocationSize = 50)
public class EventReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EVENTREVIEW_SEQ_GENERATOR")
    @Column(name = "eventreview_id")
    private Long id;

    private String content;
    private byte score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public EventReview(String content, byte score, User user) {
        this.content = content;
        this.score = score;
        this.user = user;
    }
}
