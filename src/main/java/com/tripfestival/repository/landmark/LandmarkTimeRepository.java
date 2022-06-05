package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandmarkTimeRepository extends JpaRepository<LandmarkTime, Long> {
    List<LandmarkTime> findByLandmark(Landmark landmark);
}
