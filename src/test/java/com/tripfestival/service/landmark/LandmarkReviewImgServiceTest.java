package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkReview;
import com.tripfestival.domain.landmark.LandmarkReviewImg;
import com.tripfestival.domain.user.User;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkReviewImgProcessDto;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.landmark.LandmarkReviewImgRepository;
import com.tripfestival.repository.landmark.LandmarkReviewRepository;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkReviewImgListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LandmarkReviewImgServiceTest {

    @Autowired
    LandmarkReviewImgService landmarkReviewImgService;

    @Autowired
    LandmarkReviewRepository landmarkReviewRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    LandmarkReviewImgRepository landmarkReviewImgRepository;

    @Autowired
    UserRepository userRepository;

    @MockBean
    FileService fileService;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    LandmarkReview landmarkReview;
    LandmarkReviewImg landmarkReviewImg;

    @BeforeEach
    void setup() {
        List<String> filePathList = new ArrayList<>();
        filePathList.add("test1.jpg");
        filePathList.add("test2.jpg");
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFileList())).thenReturn(filePathList);


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

        Landmark landmark = Landmark.builder()
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
                .build();
        landmarkReviewRepository.save(landmarkReview);

        landmarkReviewImg = LandmarkReviewImg.builder()
                .img("/test.jpg")
                .landmarkReview(landmarkReview)
                .build();
        landmarkReviewImgRepository.save(landmarkReviewImg);
    }

    @Test
    void LANDMARK_REVIEW_IMG_INSERT_TEST() {
        //given
        LandmarkReviewImgProcessDto landmarkReviewImgProcessDto = LandmarkReviewImgProcessDto.builder()
                .files(FileTestUtil.getMockMultipartFileList())
                .landmarkReviewId(landmarkReview.getId())
                .build();

        //when
        ResponseVo responseVo = landmarkReviewImgService.landmarkReviewImgInsert(landmarkReviewImgProcessDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_REVIEW_IMG_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkReviewImgService.landmarkReviewImgDelete(landmarkReviewImg.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_REVIEW_IMG_LIST_SELECT_TEST() {
        //given
        List<LandmarkReviewImg> landmarkReviewImgList = new ArrayList<>();
        landmarkReviewImgList.add(landmarkReviewImg);

        LandmarkReviewImgListVo landmarkReviewImgListVo1 = new LandmarkReviewImgListVo(landmarkReviewImgList);

        //when
        LandmarkReviewImgListVo landmarkReviewImgListVo2 = landmarkReviewImgService.landmarkReviewImgListSelect(landmarkReview.getId());

        //then
        assertThat(landmarkReviewImgListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkReviewImgListVo2);

    }
}