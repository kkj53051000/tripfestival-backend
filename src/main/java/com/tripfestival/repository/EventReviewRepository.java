package com.tripfestival.repository;

import com.tripfestival.domain.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReviewRepository extends JpaRepository<EventReview, Long> {
}
