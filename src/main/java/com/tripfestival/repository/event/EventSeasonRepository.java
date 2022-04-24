package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventSeasonRepository extends JpaRepository<EventSeason, Long> {

}
