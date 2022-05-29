package com.tripfestival.controller.hotspot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.hotspot.Hotspot;
import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
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
import com.tripfestival.vo.hotspot.HotspotNameVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotspotControllerTest extends BaseControllerTest {

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
    HotspotRepository hotspotRepository;

    @Autowired
    LandmarkHashTagRepository landmarkHashTagRepository;

    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    HotspotType hotspotType;
    Hotspot hotspot;

    @BeforeEach
    void setup() {
        WorldCountry worldCountry = WorldCountry.builder()
                .name("korea")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("seoul")
                .worldCountry(worldCountry)
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("gangnamgu")
                .worldCountryCity(worldCountryCity)
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        landmark = Landmark.builder()
                .name("landmark")
                .worldCountryCityRegion(worldCountryCityRegion)
                .build();
        landmarkRepository.save(landmark);

        hotspotType = HotspotType.builder()
                .name("hotspotType")
                .build();
        hotspotTypeRepository.save(hotspotType);

        hotspot = Hotspot.builder()
                .landmark(landmark)
                .hotspotType(hotspotType)
                .build();
        hotspotRepository.save(hotspot);
    }

    @Test
    void HOTSPOT_PROCESS_TEST() throws Exception {
        //given
        HotspotProcessRequest hotspotProcessRequest = HotspotProcessRequest.builder()
                .landmarkId(landmark.getId())
                .hotspotTypeId(hotspotType.getId())
                .build();

        String req = objectMapper.writeValueAsString(hotspotProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotspotProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotspotRemove/" + hotspot.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_LIST_TEST() throws Exception {
        //given
        List<Hotspot> hotspotList = new ArrayList<>();
        hotspotList.add(hotspot);

        // Landmark HashTag
        LandmarkHashTag landmarkHashTag = LandmarkHashTag.builder()
                .name("test")
                .landmark(landmark)
                .build();
        landmarkHashTagRepository.save(landmarkHashTag);

        List<LandmarkHashTag> landmarkHashTagList = new ArrayList<>();
        landmarkHashTagList.add(landmarkHashTag);

        List<List<LandmarkHashTag>> landmarkHashTagListList = new ArrayList<>();
        landmarkHashTagListList.add(landmarkHashTagList);

        // Response
        HotspotListVo hotspotListVo = new HotspotListVo(hotspotList, landmarkHashTagListList);

        String response = objectMapper.writeValueAsString(hotspotListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotspotList")
                .param("hotspotTypeId", String.valueOf(hotspotType.getId()))
                .param("worldCountryCityId", String.valueOf(worldCountryCity.getId()))
                .param("worldCountryCityRegionId", String.valueOf(worldCountryCityRegion.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_ALL_LIST_TEST() throws Exception {
        //given
        List<Hotspot> hotspotList = new ArrayList<>();
        hotspotList.add(hotspot);

        HotspotAllListVo hotspotAllListVo = new HotspotAllListVo(hotspotList);

        String response = objectMapper.writeValueAsString(hotspotAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotspotAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_NAME_TEST() throws Exception {
        //given
        HotspotNameVo hotspotNameVo = HotspotNameVo.builder()
                .hotspotName(hotspot.getLandmark().getName())
                .build();

        String response = objectMapper.writeValueAsString(hotspotNameVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotspotName/" + hotspot.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}