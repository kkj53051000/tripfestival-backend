package com.tripfestival.repository;

import com.tripfestival.domain.WorldCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldCountryRepository extends JpaRepository<WorldCountry, Long> {
}
