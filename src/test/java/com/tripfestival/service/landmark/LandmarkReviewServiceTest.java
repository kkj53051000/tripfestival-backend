package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkReview;
import com.tripfestival.domain.user.User;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.landmark.LandmarkReviewRepository;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkReviewProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkReviewListVo;
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
class LandmarkReviewServiceTest {

    @Autowired
    LandmarkReviewService landmarkReviewService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LandmarkReviewRepository landmarkReviewRepository;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    Landmark landmark;
    LandmarkReview landmarkReview;

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

        User user = User.builder()
                .uid("user")
                .build();
        userRepository.save(user);

        landmarkReview = LandmarkReview.builder()
                .content("landmarkReview")
                .score((byte) 5)
                .user(user)
                .landmark(landmark)
                .build();
        landmarkReviewRepository.save(landmarkReview);
    }

    @Test
    void LANDMARK_REVIEW_INSERT_TEST() {
        //given
        LandmarkReviewProcessRequest landmarkReviewProcessRequest = LandmarkReviewProcessRequest.builder()
                .landmarkId(landmark.getId())
                .content("content")
                .score((byte)5)
                .build();

        //when
        ResponseVo responseVo = landmarkReviewService.landmarkReviewInsert(landmarkReviewProcessRequest);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_REVIEW_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkReviewService.landmarkReviewDelete(landmarkReview.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_REVIEW_LIST_SELECT_TEST() {
        //given
        List<LandmarkReview> landmarkReviewList = new ArrayList<>();
        landmarkReviewList.add(landmarkReview);

        LandmarkReviewListVo landmarkReviewListVo1 = new LandmarkReviewListVo(landmarkReviewList);

        //when
        LandmarkReviewListVo landmarkReviewListVo2 = landmarkReviewService.landmarkReviewListSelect(landmark.getId());

        //then
        assertThat(landmarkReviewListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkReviewListVo2);

    }
}