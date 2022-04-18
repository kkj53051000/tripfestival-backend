package com.tripfestival.repository;

import com.tripfestival.domain.LandmarkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandmarkTimeRepository extends JpaRepository<LandmarkTime, Long> {
}
