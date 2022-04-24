package com.tripfestival.repository.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventImgRepository extends JpaRepository<EventImg, Long> {
    Optional<List<EventImg>> findByEvent(Event event);
}
