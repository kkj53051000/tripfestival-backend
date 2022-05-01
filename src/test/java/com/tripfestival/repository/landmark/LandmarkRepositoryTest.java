package com.tripfestival.repository.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class LandmarkRepositoryTest {

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Test
    @Transactional
    void LANDMARK_SAVE__TEST() {
        // given
        WorldCountry worldCountry = new WorldCountry("t", "t", "t", "t", "t");
        worldCountryRepository.save(worldCountry);
        WorldCountryCity worldCountryCity = new WorldCountryCity("t", "t", worldCountry);
        worldCountryCityRepository.save(worldCountryCity);
        WorldCountryCityRegion worldCountryCityRegion = new WorldCountryCityRegion("t", "t", worldCountryCity);
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        // when
        Landmark landmark = Landmark.builder()
                .name("t")
                .description("t")
                .address("t")
                .homepage("t")
                .worldCountryCityRegion(worldCountryCityRegion)
                .build();

        landmarkRepository.save(landmark);

        // then
        Assertions.assertEquals(landmark, landmarkRepository.findById(landmark.getId()).get());
    }

}