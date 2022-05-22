package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    Optional<String> findByName(String name);

    List<Landmark> findByWorldCountryCityRegion_WorldCountryCity(WorldCountryCity worldCountryCity);

    List<Landmark> findByWorldCountryCityRegion (WorldCountryCityRegion worldCountryCityRegion);

    List<Landmark> findAllByNameStartingWith(String searchWord);
}
