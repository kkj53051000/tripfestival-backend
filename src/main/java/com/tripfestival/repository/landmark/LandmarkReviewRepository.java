package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LandmarkReviewRepository extends JpaRepository<LandmarkReview, Long> {
    Optional<List<LandmarkReview>> findByLandmark(Landmark landmark);
}
