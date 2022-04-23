package com.tripfestival.service;

import com.tripfestival.domain.WorldCountry;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.repository.WorldCountryRepository;
import com.tripfestival.request.LandmarkProcessRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LandmarkServiceTest {

    @Autowired
    LandmarkService landmarkService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Test
    @Transactional
    void LANDMARK_INSERT_TEST() {
        // given
        WorldCountry worldCountry = new WorldCountry("t", "t", "t", "t", "t");
        worldCountryRepository.save(worldCountry);
        WorldCountryCity worldCountryCity = new WorldCountryCity("t", "t", worldCountry);
        worldCountryCityRepository.save(worldCountryCity);

        LandmarkProcessRequest landmarkProcessRequest = LandmarkProcessRequest.builder()
                .name("t")
                .description("t")
                .address("t")
                .homepage("t")
                .worldCountryCityId(worldCountryCity.getId())
                .build();

        // when
        landmarkService.landmarkInsert(landmarkProcessRequest);

        //then
        Assertions.assertNotNull(landmarkRepository.findByName(landmarkProcessRequest.getName()).get());

    }
}