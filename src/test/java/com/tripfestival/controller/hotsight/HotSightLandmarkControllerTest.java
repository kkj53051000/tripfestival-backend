package com.tripfestival.controller.hotsight;

import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.hotsight.HotSightLandmark;
import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.domain.hotsight.HotSightTwo;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.hotsight.HotSightLandmarkRepository;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.repository.hotsight.HotSightTwoRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.hotsight.HotSightLandmarkHotSightTwoModifyRequest;
import com.tripfestival.request.hotsight.HotSightLandmarkProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotsight.HotSightLandmarkAllListVo;
import com.tripfestival.vo.hotsight.HotSightLandmarkListVo;
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
class HotSightLandmarkControllerTest extends BaseControllerTest {

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


    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    HotSightTwo hotSightTwo;
    HotSightLandmark hotSightLandmark;

    @BeforeEach
    void setup() {
        WorldCountry worldCountry = WorldCountry.builder()
                .name("korea")
                .build();
        worldCountryRepository.save(worldCountry);

        WorldCountryCity worldCountryCity = WorldCountryCity.builder()
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

        HotSightOne hotSightOne = HotSightOne.builder()
                .name("special1")
                .build();
        hotSightOneRepository.save(hotSightOne);

        hotSightTwo = HotSightTwo.builder()
                .name("special2")
                .hotSightOne(hotSightOne)
                .build();
        hotSightTwoRepository.save(hotSightTwo);

        hotSightLandmark = HotSightLandmark.builder()
                .landmark(landmark)
                .hotSightTwo(hotSightTwo)
                .build();
        hotSightLandmarkRepository.save(hotSightLandmark);
    }

    @Test
    void HOTSIGHT_LANDMARK_PROCESS_TEST() throws Exception {
        //given
        HotSightLandmarkProcessRequest hotSightLandmarkProcessRequest = HotSightLandmarkProcessRequest.builder()
                .landmarkId(landmark.getId())
                .hotSightTwoId(hotSightTwo.getId())
                .build();

        String req = objectMapper.writeValueAsString(hotSightLandmarkProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightLandmarkProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_LANDMARK_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightLandmarkRemove/" + hotSightLandmark.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_LANDMARK_HOTSIGHT_TWO_MODIFY_TEST() throws Exception {
        //given
        HotSightLandmarkHotSightTwoModifyRequest hotSightLandmarkHotSightTwoModifyRequest = HotSightLandmarkHotSightTwoModifyRequest.builder()
                .hotSightTowId(hotSightTwo.getId())
                .build();

        String req = objectMapper.writeValueAsString(hotSightLandmarkHotSightTwoModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightLandmarkHotSightTwoModify/" + hotSightLandmark.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_LANDMARK_LIST_TEST() throws Exception {
        //given
        List<HotSightLandmark> hotSightLandmarkList = new ArrayList<>();
        hotSightLandmarkList.add(hotSightLandmark);

        HotSightLandmarkListVo hotSightLandmarkListVo = new HotSightLandmarkListVo(hotSightLandmarkList);
        String response = objectMapper.writeValueAsString(hotSightLandmarkListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotSightLandmarkList")
                .characterEncoding("UTF-8")
                .param("hotSightTwoId", hotSightTwo.getId().toString()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_LANDMARK_ALL_LIST_TEST() throws Exception {
        //given
        List<HotSightLandmark> hotSightLandmarkList = new ArrayList<>();
        hotSightLandmarkList.add(hotSightLandmark);

        HotSightLandmarkAllListVo hotSightLandmarkAllListVo = new HotSightLandmarkAllListVo(hotSightLandmarkList);

        String response = objectMapper.writeValueAsString(hotSightLandmarkAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotSightLandmarkAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}