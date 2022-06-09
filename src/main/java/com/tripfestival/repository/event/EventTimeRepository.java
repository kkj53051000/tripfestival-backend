package com.tripfestival.repository.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventTimeRepository extends JpaRepository<EventTime, Long> {
    List<EventTime> findByEvent(Event event);
}
