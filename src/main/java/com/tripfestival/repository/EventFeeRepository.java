package com.tripfestival.repository;

import com.tripfestival.domain.EventFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFeeRepository extends JpaRepository<EventFee, Long> {
}
