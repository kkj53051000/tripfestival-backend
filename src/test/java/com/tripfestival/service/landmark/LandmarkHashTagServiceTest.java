package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkHashTagProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkHashTagAllListVo;
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
class LandmarkHashTagServiceTest {

    @Autowired
    LandmarkHashTagService landmarkHashTagService;

    @Autowired
    LandmarkHashTagRepository landmarkHashTagRepository;

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
    LandmarkHashTag landmarkHashTag;

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

        landmarkHashTag = LandmarkHashTag.builder()
                .name("landmarkHashTag")
                .landmark(landmark)
                .build();
        landmarkHashTagRepository.save(landmarkHashTag);
    }

    @Test
    void LANDMARK_HASH_TAG_INSERT_TEST() {
        //given
        LandmarkHashTagProcessRequest landmarkHashTagProcessRequest = LandmarkHashTagProcessRequest.builder()
                .name("landmarkHashTag")
                .landmarkId(landmark.getId())
                .build();

        //when
        ResponseVo responseVo = landmarkHashTagService.landmarkHashTagInsert(landmarkHashTagProcessRequest);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_HASH_TAG_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkHashTagService.landmarkHashTagDelete(landmarkHashTag.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_HASH_TAG_ALL_LIST_SELECT_TEST() {
        //given
        List<LandmarkHashTag> landmarkHashTagList = new ArrayList<>();
        landmarkHashTagList.add(landmarkHashTag);

        LandmarkHashTagAllListVo landmarkHashTagAllListVo1 = new LandmarkHashTagAllListVo(landmarkHashTagList);

        //when
        LandmarkHashTagAllListVo landmarkHashTagAllListVo2 = landmarkHashTagService.landmarkHashTagAllListSelect();

        //then
        assertThat(landmarkHashTagAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkHashTagAllListVo2);

    }
}