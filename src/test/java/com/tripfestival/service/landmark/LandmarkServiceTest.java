package com.tripfestival.service.landmark;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkProcessDto;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.service.landmark.LandmarkService;
import com.tripfestival.util.FileTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class LandmarkServiceTest {

    @Autowired
    LandmarkService landmarkService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

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
        WorldCountryCityRegion worldCountryCityRegion = new WorldCountryCityRegion("t", "t", worldCountryCity);
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        LandmarkProcessDto landmarkProcessDto = LandmarkProcessDto.builder()
                .name("test")
                .file(FileTestUtil.getMockMultipartFile())
                .description("test")
                .address("test")
                .homepage("test")
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        // when
        landmarkService.landmarkInsert(landmarkProcessDto);

        //then
        Assertions.assertNotNull(landmarkRepository.findByName(landmarkProcessDto.getName()).get());

    }
}