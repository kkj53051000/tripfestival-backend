package com.tripfestival.repository;

import com.tripfestival.domain.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTimeRepository extends JpaRepository<EventTime, Long> {
}
