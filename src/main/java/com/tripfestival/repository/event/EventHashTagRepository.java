package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventHashTagRepository extends JpaRepository<EventHashTag, Long> {
}
