package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkTime;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkTimeModifyDto;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.landmark.LandmarkTimeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkTimeAllListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LandmarkTimeServiceTest {

    @Autowired
    LandmarkTimeService landmarkTimeService;

    @Autowired
    LandmarkTimeRepository landmarkTimeRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    Landmark landmark;
    LandmarkTime landmarkTime;

    @BeforeEach
    void setup() {
        WorldCountry worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);

        WorldCountryCity worldCountryCity = WorldCountryCity.builder()
                .name("worldCountryCity")
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("worldCountryCityRegion")
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        landmark = Landmark.builder()
                .name("name")
                .img("Test.jpg")
                .description("de")
                .address("address")
                .homepage("test")
                .contentId(LANDMARK_CONTENT_ID)
                .worldCountryCityRegion(worldCountryCityRegion)
                .build();
        landmarkRepository.save(landmark);

        landmarkTime = LandmarkTime.builder()
                .title("title")
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .landmark(landmark)
                .build();
        landmarkTimeRepository.save(landmarkTime);
    }

    @Test
    void LANDMARK_TIME_INSERT_TEST() {
        //given
        LandmarkTimeProcessRequest landmarkTimeProcessRequest = LandmarkTimeProcessRequest.builder()
                .title("title")
                .startTime(String.valueOf(LocalTime.now()))
                .endTime(String.valueOf(LocalTime.now()))
                .landmarkId(landmark.getId())
                .build();

        //when
        ResponseVo responseVo = landmarkTimeService.landmarkTimeInsert(landmarkTimeProcessRequest);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_TIME_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkTimeService.landmarkTimeDelete(landmarkTime.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_TIME_ALERT_TEST() {
        //given
        LandmarkTimeModifyDto landmarkTimeModifyDto = LandmarkTimeModifyDto.builder()
                .landmarkTimeId(landmarkTime.getId())
                .title("title")
                .startTime(String.valueOf(LocalTime.now()))
                .endTime(String.valueOf(LocalTime.now()))
                .build();


        //when
        ResponseVo responseVo = landmarkTimeService.landmarkTimeAlert(landmarkTimeModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_TIME_ALL_LIST_SELECT_TEST() {
        //given
        List<LandmarkTime> landmarkTimeList = new ArrayList<>();
        landmarkTimeList.add(landmarkTime);

        LandmarkTimeAllListVo landmarkTimeAllListVo1 = new LandmarkTimeAllListVo(landmarkTimeList);

        //when
        LandmarkTimeAllListVo landmarkTimeAllListVo2 = landmarkTimeService.landmarkTimeAllListSelect();

        //then
        assertThat(landmarkTimeAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkTimeAllListVo2);

    }

}