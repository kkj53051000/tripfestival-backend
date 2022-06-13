package com.tripfestival.service.naturehotspot;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.naturehotspot.NatureHotspotImgModifyDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotListDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotNatureHotspotTypeModifyDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotProcessDto;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotAllListVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotListVo;
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
class NatureHotspotServiceTest {

    @Autowired
    NatureHotspotService natureHotspotService;

    @Autowired
    NatureHotspotRepository natureHotspotRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    NatureHotspotTypeRepository natureHotspotTypeRepository;

    @Autowired
    LandmarkHashTagRepository landmarkHashTagRepository;

    @MockBean
    FileService fileService;

    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    Landmark landmark;
    NatureHotspotType natureHotspotType;
    NatureHotspot natureHotspot;
    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("/test.jpg");

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

        natureHotspotType = NatureHotspotType.builder()
                .name("name")
                .img("img")
                .build();
        natureHotspotTypeRepository.save(natureHotspotType);

        natureHotspot = NatureHotspot.builder()
                .img("img")
                .landmark(landmark)
                .natureHotspotType(natureHotspotType)
                .build();
        natureHotspotRepository.save(natureHotspot);
    }

    @Test
    void NATURE_HOT_SPOT_INSERT_TEST() {
        //given
        NatureHotspotProcessDto natureHotspotProcessDto = NatureHotspotProcessDto.builder()
                .file(FileTestUtil.getMockMultipartFile())
                .landmarkId(landmark.getId())
                .natureHotspotTypeId(natureHotspotType.getId())
                .build();

        //when
        ResponseVo responseVo = natureHotspotService.natureHotspotInsert(natureHotspotProcessDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void NATURE_HOTSPOT_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = natureHotspotService.natureHotspotDelete(natureHotspot.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void NATURE_HOTSPOT_NATURE_HOTSPOT_TYPE_ALERT_TEST() {
        //given
        NatureHotspotNatureHotspotTypeModifyDto natureHotspotNatureHotspotTypeModifyDto = NatureHotspotNatureHotspotTypeModifyDto.builder()
                .natureHotspotId(natureHotspot.getId())
                .natureHotspotTypeId(natureHotspotType.getId())
                .build();

        //when
        ResponseVo responseVo = natureHotspotService.natureHotspotNatureHotspotTypeAlert(natureHotspotNatureHotspotTypeModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void NATURE_HOTSPOT_IMG_ALERT_TEST() {
        //given
        NatureHotspotImgModifyDto natureHotspotImgModifyDto = NatureHotspotImgModifyDto.builder()
                .natureHotspotId(natureHotspot.getId())
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = natureHotspotService.natureHotspotImgAlert(natureHotspotImgModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void NATURE_HOTSPOT_LIST_SELECT_TEST() {
        //given
        NatureHotspotListDto natureHotspotListDto = NatureHotspotListDto.builder()
                .natureHotspotTypeId(natureHotspotType.getId())
                .worldCountryCityId(worldCountryCity.getId())
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        List<NatureHotspot> natureHotspotList = new ArrayList<>();
        natureHotspotList.add(natureHotspot);

        // Landmark HashTag List
        List<List<LandmarkHashTag>> landmarkHashTagListVoList = new ArrayList<>();

        for (NatureHotspot natureHotspot : natureHotspotList) {
            List<LandmarkHashTag> landmarkHashTagList = landmarkHashTagRepository.findByLandmark(natureHotspot.getLandmark());

            if (landmarkHashTagList.size() == 0) {
                landmarkHashTagListVoList.add(new ArrayList<LandmarkHashTag>());
            }else{
                System.out.println(landmarkHashTagList.size());

                List<LandmarkHashTag> items = landmarkHashTagList;
                landmarkHashTagListVoList.add(items);
            }
        }

        NatureHotspotListVo natureHotspotListVo1 = new NatureHotspotListVo(natureHotspotList, landmarkHashTagListVoList);

        //when
        NatureHotspotListVo natureHotspotListVo2 = natureHotspotService.natureHotspotListSelect(natureHotspotListDto);

        //then
        assertThat(natureHotspotListVo1)
                .usingRecursiveComparison()
                .isEqualTo(natureHotspotListVo2);

    }

    @Test
    void NATURE_HOTSPOT_ALL_LIST_SELECT_TEST() {
        //given
        List<NatureHotspot> natureHotspotList = new ArrayList<>();
        natureHotspotList.add(natureHotspot);

        NatureHotspotAllListVo natureHotspotAllListVo1 = new NatureHotspotAllListVo(natureHotspotList);

        //when
        NatureHotspotAllListVo natureHotspotAllListVo2 = natureHotspotService.natureHotspotAllListSelect();

        //then
        assertThat(natureHotspotAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(natureHotspotAllListVo2);

    }
}