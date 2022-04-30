package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
