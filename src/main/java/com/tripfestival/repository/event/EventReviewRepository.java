package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReviewRepository extends JpaRepository<EventReview, Long> {
}
