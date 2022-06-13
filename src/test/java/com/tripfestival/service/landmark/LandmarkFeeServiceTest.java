package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkFee;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkFeeModifyDto;
import com.tripfestival.repository.landmark.LandmarkFeeRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkFeeAllListVo;
import com.tripfestival.vo.landmark.LandmarkFeeListVo;
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
class LandmarkFeeServiceTest {

    @Autowired
    LandmarkFeeService landmarkFeeService;

    @Autowired
    LandmarkFeeRepository landmarkFeeRepository;

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
    LandmarkFee landmarkFee;

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

        landmarkFee = LandmarkFee.builder()
                .title("landmarkFee")
                .price(100)
                .landmark(landmark)
                .build();
        landmarkFeeRepository.save(landmarkFee);
    }

    @Test
    void LANDMARK_FEE_INSERT_TEST() {
        //given
        LandmarkFeeProcessRequest landmarkFeeProcessRequest = LandmarkFeeProcessRequest.builder()
                .title("landmarkFee")
                .price(100)
                .landmarkId(landmark.getId())
                .build();

        //when
        ResponseVo responseVo = landmarkFeeService.landmarkFeeInsert(landmarkFeeProcessRequest);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_FEE_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkFeeService.landmarkFeeDelete(landmarkFee.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_FEE_ALERT_TEST() {
        //given
        LandmarkFeeModifyDto landmarkFeeModifyDto = LandmarkFeeModifyDto.builder()
                .landmarkFeeId(landmarkFee.getId())
                .title("changeLandmarkFee")
                .price(1000)
                .build();

        //when
        ResponseVo responseVo = landmarkFeeService.landmarkFeeAlert(landmarkFeeModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_FEE_LIST_SELECT_TEST() {
        //given
        List<LandmarkFee> landmarkFeeList = new ArrayList<>();
        landmarkFeeList.add(landmarkFee);

        LandmarkFeeListVo landmarkFeeListVo1 = new LandmarkFeeListVo(landmarkFeeList);

        //when
        LandmarkFeeListVo landmarkFeeListVo2 = landmarkFeeService.landmarkFeeListSelect(landmark.getId());

        //then
        assertThat(landmarkFeeListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkFeeListVo2);

    }

    @Test
    void LANDMARK_FEE_ALL_LIST_SELECT_TEST() {
        //given
        List<LandmarkFee> landmarkFeeList = new ArrayList<>();
        landmarkFeeList.add(landmarkFee);

        LandmarkFeeAllListVo landmarkFeeAllListVo1 = new LandmarkFeeAllListVo(landmarkFeeList);

        //when
        LandmarkFeeAllListVo landmarkFeeAllListVo2 = landmarkFeeService.landmarkFeeAllListSelect();

        //then
        assertThat(landmarkFeeAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkFeeAllListVo2);

    }
}