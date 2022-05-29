package com.tripfestival.controller.landmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkHashTagProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkHashTagAllListVo;
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
class LandmarkHashTagControllerTest extends BaseControllerTest {

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

    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    LandmarkHashTag landmarkHashTag;

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

        landmarkHashTag = LandmarkHashTag.builder()
                .name("tag")
                .landmark(landmark)
                .build();
        landmarkHashTagRepository.save(landmarkHashTag);
    }

    @Test
    void LANDMARK_HASH_TAG_PROCESS_TEST() throws Exception {
        //given
        LandmarkHashTagProcessRequest landmarkHashTagProcessRequest = LandmarkHashTagProcessRequest.builder()
                .name("test")
                .landmarkId(landmark.getId())
                .build();

        String req = objectMapper.writeValueAsString(landmarkHashTagProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkHashTagProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_HASH_TAG_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkHashTagRemove/" + landmarkHashTag.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_HASH_TAG_ALL_LIST_TEST() throws Exception {
        //given
        List<LandmarkHashTag> landmarkHashTagList = new ArrayList<>();
        landmarkHashTagList.add(landmarkHashTag);

        LandmarkHashTagAllListVo landmarkHashTagAllListVo = new LandmarkHashTagAllListVo(landmarkHashTagList);

        String response = objectMapper.writeValueAsString(landmarkHashTagAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkHashTagAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}