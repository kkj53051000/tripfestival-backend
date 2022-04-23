package com.tripfestival.repository;

import com.tripfestival.domain.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    Optional<String> findByName(String name);
}
