package com.tripfestival.repository.world;

import com.tripfestival.domain.world.WorldCountry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorldCountryRepository extends JpaRepository<WorldCountry, Long> {
    Optional<WorldCountry> findByName(String name);
}
