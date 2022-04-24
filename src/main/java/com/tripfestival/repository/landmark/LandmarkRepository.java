package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    Optional<String> findByName(String name);
}
