package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandmarkHashTagRepository extends JpaRepository<LandmarkHashTag, Long> {
    List<LandmarkHashTag> findByLandmark(Landmark landmark);
}
