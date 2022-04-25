package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LandmarkImgRepository extends JpaRepository<LandmarkImg, Long> {
    Optional<List<LandmarkImg>> findByLandmark(Landmark landmark);
}
