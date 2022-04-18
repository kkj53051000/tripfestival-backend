package com.tripfestival.repository;

import com.tripfestival.domain.Event;
import com.tripfestival.domain.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
