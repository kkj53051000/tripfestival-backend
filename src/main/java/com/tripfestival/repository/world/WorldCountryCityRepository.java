package com.tripfestival.repository.world;

import com.tripfestival.domain.world.WorldCountryCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorldCountryCityRepository extends JpaRepository<WorldCountryCity, Long> {
    Optional<WorldCountryCity> findByName(String name);

    List<WorldCountryCity> findByNameStartsWith(String searchName);
}
