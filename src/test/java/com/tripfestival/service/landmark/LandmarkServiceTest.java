package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.landmark.LandmarkImg;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkListDto;
import com.tripfestival.dto.landmark.LandmarkModifyDto;
import com.tripfestival.dto.landmark.LandmarkProcessDto;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkImgRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.service.landmark.LandmarkService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkAllCountVo;
import com.tripfestival.vo.landmark.LandmarkAllListVo;
import com.tripfestival.vo.landmark.LandmarkListVo;
import com.tripfestival.vo.landmark.LandmarkVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @Autowired
    LandmarkHashTagRepository landmarkHashTagRepository;

    @Autowired
    LandmarkImgRepository landmarkImgRepository;

    @MockBean
    FileService fileService;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    WorldCountryCityRegion worldCountryCityRegion;
    WorldCountryCity worldCountryCity;
    Landmark landmark;
    LandmarkImg landmarkImg;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile()))
                .thenReturn("test.jpg");

        WorldCountry worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("worldCountryCity")
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion = WorldCountryCityRegion.builder()
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
    void LANDMARK_INSERT_TEST() {
        //given
        LandmarkProcessDto landmarkProcessDto = LandmarkProcessDto.builder()
                .file(FileTestUtil.getMockMultipartFile())
                .name("name")
                .description("de")
                .address("add")
                .homepage("home")
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        //when
        ResponseVo responseVo = landmarkService.landmarkInsert(landmarkProcessDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkService.landmarkDelete(landmark.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_CLEAR_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = landmarkService.landmarkClearDelete(landmark.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_ALERT_TEST() {
        //given
        LandmarkModifyDto landmarkModifyDto = LandmarkModifyDto.builder()
                .landmarkId(landmark.getId())
                .name("name")
                .description("description")
                .address("address")
                .homepage("homepage")
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        //when
        ResponseVo responseVo = landmarkService.landmarkAlert(landmarkModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void LANDMARK_LIST_SELECT_TEST() {
        //given
        LandmarkListDto landmarkListDto = LandmarkListDto.builder()
                .worldCountryCityId(worldCountryCity.getId())
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(landmark);

        // Landmark HashTag List
        List<List<LandmarkHashTag>> landmarkHashTagListVoList = new ArrayList<>();

        for (Landmark landmark : landmarkList) {
            List<LandmarkHashTag> landmarkHashTagList = landmarkHashTagRepository.findByLandmark(landmark);

            if (landmarkHashTagList.size() == 0) {
                landmarkHashTagListVoList.add(new ArrayList<LandmarkHashTag>());
            }else{
                System.out.println(landmarkHashTagList.size());

                List<LandmarkHashTag> items = landmarkHashTagList;
                landmarkHashTagListVoList.add(items);
            }
        }

        LandmarkListVo landmarkListVo1 = new LandmarkListVo(landmarkList, landmarkHashTagListVoList);

        //when
        LandmarkListVo landmarkListVo2 = landmarkService.landmarkListSelect(landmarkListDto);

        //then
        assertThat(landmarkListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkListVo2);

    }

    @Test
    void LANDMARK_SELECT_TEST() {
        //given
        List<LandmarkImg> landmarkImgList = new ArrayList<>();
        landmarkImgList.add(landmarkImg);

        LandmarkVo landmarkVo1 = new LandmarkVo(landmark, landmarkImgList);

        //when
        LandmarkVo landmarkVo2 = landmarkService.landmarkSelect(landmark.getId());

        //then
        assertThat(landmarkVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkVo2);

    }

    @Test
    void LANDMARK_ALL_LIST_SELECT_TEST() {
        //given
        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(landmark);

        LandmarkAllListVo landmarkAllListVo1 = new LandmarkAllListVo(landmarkList);

        //when
        LandmarkAllListVo landmarkAllListVo2 = landmarkService.landmarkAllListSelect();

        //then
        assertThat(landmarkAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkAllListVo2);

    }

    @Test
    void LANDMARK_ALL_COUNT_SELECT_TEST() {
        //given
        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(landmark);

        LandmarkAllCountVo landmarkAllCountVo1 = new LandmarkAllCountVo((long)landmarkList.size());

        //when
        LandmarkAllCountVo landmarkAllCountVo2 = landmarkService.landmarkAllCountSelect();

        //then
        assertThat(landmarkAllCountVo1)
                .usingRecursiveComparison()
                .isEqualTo(landmarkAllCountVo2);

    }
}