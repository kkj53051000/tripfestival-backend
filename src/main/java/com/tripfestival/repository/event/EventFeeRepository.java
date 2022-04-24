package com.tripfestival.repository.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventFeeRepository extends JpaRepository<EventFee, Long> {
    Optional<List<EventFee>> findByEvent(Event event);
}
