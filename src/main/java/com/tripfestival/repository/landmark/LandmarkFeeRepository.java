package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LandmarkFeeRepository extends JpaRepository<LandmarkFee, Long> {
    List<LandmarkFee> findByLandmark(Landmark landmark);
}
