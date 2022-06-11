package com.tripfestival.service.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.hotspot.HotspotListDto;
import com.tripfestival.repository.hotspot.HotspotRepository;
import com.tripfestival.repository.hotspot.HotspotTypeRepository;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotAllListVo;
import com.tripfestival.vo.hotspot.HotspotListVo;
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
class HotspotServiceTest {

    @Autowired
    HotspotService hotspotService;

    @Autowired
    HotspotRepository hotspotRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    HotspotTypeRepository hotspotTypeRepository;

    @Autowired
    LandmarkHashTagRepository landmarkHashTagRepository;


    private final String LANDMARK_CONTENT_ID = "1";
    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    Landmark landmark;
    HotspotType hotspotType;
    Hotspot hotspot;
    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;

    @BeforeEach
    void setup() {
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

        hotspotType = HotspotType.builder()
                .name("hotspotType")
                .img("test.jpg")
                .build();
        hotspotTypeRepository.save(hotspotType);

        hotspot = Hotspot.builder()
                .landmark(landmark)
                .hotspotType(hotspotType)
                .build();
        hotspotRepository.save(hotspot);
    }

    @Test
    void HOTSPOT_INSERT_TEST() {
        //given
        HotspotProcessRequest hotspotProcessRequest = HotspotProcessRequest.builder()
                .landmarkId(landmark.getId())
                .hotspotTypeId(hotspotType.getId())
                .build();

        //when
        ResponseVo responseVo = hotspotService.hotspotInsert(hotspotProcessRequest);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOTSPOT_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = hotspotService.hotspotDelete(hotspot.getId());

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOTSPOT_LIST_SELECT_TEST() {
        //given
        HotspotListDto hotspotListDto = HotspotListDto.builder()
                .hotspotTypeId(hotspotType.getId())
                .worldCountryCityId(worldCountryCity.getId())
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        List<Hotspot> hotspotList = new ArrayList<>();
        hotspotList.add(hotspot);

        // Landmark HashTag List
        List<List<LandmarkHashTag>> landmarkHashTagListVoList = new ArrayList<>();

        for (Hotspot hotspot : hotspotList) {
            List<LandmarkHashTag> landmarkHashTagList = landmarkHashTagRepository.findByLandmark(hotspot.getLandmark());

            if (landmarkHashTagList.size() == 0) {
                landmarkHashTagListVoList.add(new ArrayList<LandmarkHashTag>());
            }else{

                List<LandmarkHashTag> items = landmarkHashTagList;
                landmarkHashTagListVoList.add(items);
            }
        }

        HotspotListVo hotspotListVo1 = new HotspotListVo(hotspotList, landmarkHashTagListVoList);

        //when
        HotspotListVo hotspotListVo2 = hotspotService.hotspotListSelect(hotspotListDto);

        //then
        assertThat(hotspotListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotspotListVo2);

    }

    @Test
    void HOTSPOT_ALL_LIST_SELECT_TEST() {
        //given
        List<Hotspot> hotspotList = new ArrayList<>();
        hotspotList.add(hotspot);

        HotspotAllListVo hotspotAllListVo1 = new HotspotAllListVo(hotspotList);

        //when
        HotspotAllListVo hotspotAllListVo2 = hotspotService.hotspotAllListSelect();

        //then
        assertThat(hotspotAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotspotAllListVo2);
    }
}