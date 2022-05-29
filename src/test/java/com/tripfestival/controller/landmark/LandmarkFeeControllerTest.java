package com.tripfestival.controller.landmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkFee;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkFeeRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkFeeModifyRequest;
import com.tripfestival.request.landmark.LandmarkFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkFeeAllListVo;
import com.tripfestival.vo.landmark.LandmarkFeeListVo;
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
class LandmarkFeeControllerTest extends BaseControllerTest {

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    LandmarkFeeRepository landmarkFeeRepository;

    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    LandmarkFee landmarkFee;

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

        landmarkFee = LandmarkFee.builder()
                .title("landmarkFee")
                .price(10000)
                .landmark(landmark)
                .build();
        landmarkFeeRepository.save(landmarkFee);
    }

    @Test
    void LANDMARK_FEE_PROCESS_TEST() throws Exception {
        //given
        LandmarkFeeProcessRequest landmarkFeeProcessRequest = LandmarkFeeProcessRequest.builder()
                .title("test")
                .price(1000)
                .landmarkId(landmark.getId())
                .build();

        String req = objectMapper.writeValueAsString(landmarkFeeProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkFeeProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_FEE_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkFeeRemove/" + landmarkFee.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_FEE_MODIFY_TEST() throws Exception {
        //given
        LandmarkFeeModifyRequest landmarkFeeModifyRequest = LandmarkFeeModifyRequest.builder()
                .price(1000)
                .title("Test")
                .build();

        String req = objectMapper.writeValueAsString(landmarkFeeModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkFeeModify/" + landmarkFee.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_FEE_LIST_TEST() throws Exception {
        //given
        List<LandmarkFee> landmarkFeeList = new ArrayList<>();
        landmarkFeeList.add(landmarkFee);

        LandmarkFeeListVo landmarkFeeListVo = new LandmarkFeeListVo(landmarkFeeList);

        String response = objectMapper.writeValueAsString(landmarkFeeListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkFeeList")
                .param("landmarkId", String.valueOf(landmark.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_FEE_ALL_LIST_TEST() throws Exception {
        //given
        List<LandmarkFee> landmarkFeeList = new ArrayList<>();
        landmarkFeeList.add(landmarkFee);

        LandmarkFeeAllListVo landmarkFeeAllListVo = new LandmarkFeeAllListVo(landmarkFeeList);

        String response = objectMapper.writeValueAsString(landmarkFeeAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkFeeAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}