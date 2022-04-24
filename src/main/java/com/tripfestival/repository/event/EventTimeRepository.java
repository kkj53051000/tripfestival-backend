package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTimeRepository extends JpaRepository<EventTime, Long> {
}
