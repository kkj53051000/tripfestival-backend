package com.tripfestival.repository.world;

import com.tripfestival.domain.world.WorldCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldCountryRepository extends JpaRepository<WorldCountry, Long> {
}
