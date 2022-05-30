package com.tripfestival.controller.landmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkTime;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.landmark.LandmarkTimeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkTimeModifyRequest;
import com.tripfestival.request.landmark.LandmarkTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkTimeAllListVo;
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
class LandmarkTimeControllerTest extends BaseControllerTest {

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    LandmarkTimeRepository landmarkTimeRepository;


    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    LandmarkTime landmarkTime;

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

        landmarkTime = LandmarkTime.builder()
                .title("landmarkTime")
                .landmark(landmark)
                .build();
        landmarkTimeRepository.save(landmarkTime);
    }


    @Test
    void LANDMARK_TIME_PROCESS_TEST() throws Exception {
        //given
        LandmarkTimeProcessRequest landmarkTimeProcessRequest = LandmarkTimeProcessRequest.builder()
                .title("landamrkTime")
                .startTime("00:00")
                .endTime("00:00")
                .landmarkId(landmark.getId())
                .build();

        String req = objectMapper.writeValueAsString(landmarkTimeProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkTimeProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_TIME_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkTimeRemove/" + landmarkTime.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_TIME_MODIFY_TEST() throws Exception {
        //given
        LandmarkTimeModifyRequest landmarkTimeModifyRequest = LandmarkTimeModifyRequest.builder()
                .title("change")
                .startTime("11:11")
                .endTime("11:11")
                .build();

        String req = objectMapper.writeValueAsString(landmarkTimeModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkTimeModify/" + landmarkTime.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_TIME_ALL_LIST_TEST() throws Exception {
        //given
        List<LandmarkTime> landmarkTimeList = new ArrayList<>();
        landmarkTimeList.add(landmarkTime);

        LandmarkTimeAllListVo landmarkTimeAllListVo = new LandmarkTimeAllListVo(landmarkTimeList);

        String response = objectMapper.writeValueAsString(landmarkTimeAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkTimeAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}