package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkImg;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkImgProcessDto;
import com.tripfestival.repository.landmark.LandmarkImgRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkImgListVo;
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
class LandmarkImgServiceTest {

    @Autowired
    LandmarkImgService landmarkImgService;

    @Autowired
    LandmarkImgRepository landmarkImgRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @MockBean
    FileService fileService;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    Landmark landmark;
    LandmarkImg landmarkImg;

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

        landmarkImg = LandmarkImg.builder()
                .img("test.jpg")
                .landmark(landmark)
                .build();
        landmarkImgRepository.save(landmarkImg);
    }

    @Test
    void LANDMARK_IMG_INSERT_TEST() {
        //given
        LandmarkImgProcessDto landmarkImgProcessDto = LandmarkImgProcessDto.builder()
                .landmarkId(landmark.getId())
                .files(FileTestUtil.getMockMultipartFileList())
                .build();

        //when
        ResponseVo responseVo = landmarkImgService.landmarkImgInsert(landmarkImgProcessDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_IMG_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkImgService.landmarkImgDelete(landmarkImg.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_IMG_LIST_SELECT_TEST() {
        //given
        List<LandmarkImg> landmarkImgList = new ArrayList<>();
        landmarkImgList.add(landmarkImg);

        LandmarkImgListVo landmarkImgListVo1 = new LandmarkImgListVo(landmarkImgList);

        //when
        LandmarkImgListVo landmarkImgListVo2 = landmarkImgService.landmarkImgListSelect(landmark.getId());

        //then
        assertThat(landmarkImgListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkImgListVo2);

    }
}