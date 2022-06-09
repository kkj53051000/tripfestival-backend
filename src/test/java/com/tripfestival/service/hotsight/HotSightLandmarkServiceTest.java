package com.tripfestival.service.hotsight;

import com.tripfestival.domain.hotsight.HotSightLandmark;
import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.domain.hotsight.HotSightTwo;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.hotSight.HotSightLandmarkHotSightTwoModifyDto;
import com.tripfestival.repository.hotsight.HotSightLandmarkRepository;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.repository.hotsight.HotSightTwoRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.hotsight.HotSightLandmarkProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotsight.HotSightLandmarkAllListVo;
import com.tripfestival.vo.hotsight.HotSightLandmarkListVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class HotSightLandmarkServiceTest {

    @Autowired
    HotSightLandmarkService hotSightLandmarkService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    HotSightOneRepository hotSightOneRepository;

    @Autowired
    HotSightTwoRepository hotSightTwoRepository;

    @Autowired
    HotSightLandmarkRepository hotSightLandmarkRepository;

    private Landmark landmark;
    private HotSightTwo hotSightTwo;
    private HotSightLandmark hotSightLandmark;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

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

        HotSightOne hotSightOne = HotSightOne.builder()
                .name("hotSightOne")
                .img("test.jpg")
                .build();
        hotSightOneRepository.save(hotSightOne);

        hotSightTwo = HotSightTwo.builder()
                .name("hotSightTwo")
                .img("test.jpg")
                .hotSightOne(hotSightOne)
                .build();
        hotSightTwoRepository.save(hotSightTwo);

        hotSightLandmark = HotSightLandmark.builder()
                .hotSightTwo(hotSightTwo)
                .landmark(landmark)
                .build();
        hotSightLandmarkRepository.save(hotSightLandmark);
    }

    @Test
    void HOT_SIGHT_LANDMARK_INSERT_TEST() {
        //given
        HotSightLandmarkProcessRequest hotSightLandmarkProcessRequest = HotSightLandmarkProcessRequest.builder()
                .landmarkId(landmark.getId())
                .hotSightTwoId(hotSightTwo.getId())
                .build();

        //when
        ResponseVo responseVo = hotSightLandmarkService.hotSightLandmarkInsert(hotSightLandmarkProcessRequest);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_LANDMARK_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = hotSightLandmarkService.hotSightLandmarkDelete(hotSightLandmark.getId());

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_LANDMARK_HOT_SIGHT_TWO_ALERT_TEST() {
        //given
        HotSightLandmarkHotSightTwoModifyDto hotSightLandmarkHotSightTwoModifyDto = HotSightLandmarkHotSightTwoModifyDto.builder()
                .hotSightLandmarkId(hotSightLandmark.getId())
                .hotSightTwoId(hotSightTwo.getId())
                .build();

        //when
        ResponseVo responseVo = hotSightLandmarkService.hotSightLandmarkHotSightTwoAlert(hotSightLandmarkHotSightTwoModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_LANDMARK_LIST_SELECT() {
        //given
        List<HotSightLandmark> hotSightLandmarkList = new ArrayList<>();
        hotSightLandmarkList.add(hotSightLandmark);

        HotSightLandmarkListVo hotSightLandmarkListVo1 = new HotSightLandmarkListVo(hotSightLandmarkList);

        //when
        HotSightLandmarkListVo hotSightLandmarkListVo2 = hotSightLandmarkService.hotSightLandmarkListSelect(hotSightTwo.getId());

        //then
        assertThat(hotSightLandmarkListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotSightLandmarkListVo2);

    }

    @Test
    void HOT_SIGHT_LANDMARK_ALL_LIST_SELECT_TEST() {
        //given
        List<HotSightLandmark> hotSightLandmarkList = new ArrayList<>();
        hotSightLandmarkList.add(hotSightLandmark);

        HotSightLandmarkAllListVo hotSightLandmarkAllListVo1 = new HotSightLandmarkAllListVo(hotSightLandmarkList);

        //when
        HotSightLandmarkAllListVo hotSightLandmarkAllListVo2 = hotSightLandmarkService.hotSightLandmarkAllListSelect();

        //then
        assertThat(hotSightLandmarkAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotSightLandmarkAllListVo2);

    }
}