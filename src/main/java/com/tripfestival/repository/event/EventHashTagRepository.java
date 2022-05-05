package com.tripfestival.repository.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventHashTagRepository extends JpaRepository<EventHashTag, Long> {
    List<EventHashTag> findByEvent(Event event);
}
