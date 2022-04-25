package com.tripfestival.repository.world;

import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorldCountryCityRegionRepository extends JpaRepository<WorldCountryCityRegion, Long> {
    Optional<List<WorldCountryCityRegion>> findByWorldCountryCity(WorldCountryCity worldCountryCity);
}