package com.tripfestival.repository;

import com.tripfestival.domain.EventSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSeasonRepository extends JpaRepository<EventSeason, Long> {
}
