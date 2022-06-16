package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.LandmarkReview;
import com.tripfestival.domain.landmark.LandmarkReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LandmarkReviewImgRepository extends JpaRepository<LandmarkReviewImg, Long> {
    List<LandmarkReviewImg> findByLandmarkReview(LandmarkReview landmarkReview);
}
