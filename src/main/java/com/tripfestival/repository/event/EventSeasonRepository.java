package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSeasonRepository extends JpaRepository<EventSeason, Long> {
}
