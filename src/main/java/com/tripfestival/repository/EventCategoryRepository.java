package com.tripfestival.repository;

import com.tripfestival.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<Event, Long> {
}
