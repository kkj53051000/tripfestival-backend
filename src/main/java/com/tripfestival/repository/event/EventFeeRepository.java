package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFeeRepository extends JpaRepository<EventFee, Long> {
}
