package com.tripfestival.repository;

import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.WorldCountry;
import com.tripfestival.domain.WorldCountryCity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LandmarkRepositoryTest {

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Test
    @Transactional
    void LANDMARK_SAVE__TEST() {
        // given
        WorldCountry worldCountry = new WorldCountry("t", "t", "t", "t", "t");
        worldCountryRepository.save(worldCountry);
        WorldCountryCity worldCountryCity = new WorldCountryCity("t", "t", worldCountry);
        worldCountryCityRepository.save(worldCountryCity);

        // when
        Landmark landmark = Landmark.builder()
                .name("t")
                .description("t")
                .address("t")
                .homepage("t")
                .worldCountryCity(worldCountryCity)
                .build();

        landmarkRepository.save(landmark);

        // then
        Assertions.assertEquals(landmark, landmarkRepository.findById(landmark.getId()).get());
    }

}