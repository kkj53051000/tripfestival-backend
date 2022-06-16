package com.tripfestival.controller.landmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.landmark.LandmarkImg;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkImgRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkModifyRequest;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkAllCountVo;
import com.tripfestival.vo.landmark.LandmarkAllListVo;
import com.tripfestival.vo.landmark.LandmarkListVo;
import com.tripfestival.vo.landmark.LandmarkVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LandmarkControllerTest extends BaseControllerTest {
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

    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    LandmarkImg landmarkImg;

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

       landmarkImg = LandmarkImg.builder()
                .img("Test.jpg")
                .landmark(landmark)
                .build();
        landmarkImgRepository.save(landmarkImg);
    }


    @Test
    void LANDMARK_PROCESS_TEST() throws Exception {
        //given
        LandmarkProcessRequest landmarkProcessRequest = LandmarkProcessRequest.builder()
                .name("test")
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        String value = objectMapper.writeValueAsString(landmarkProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/landmarkProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkRemove/" + landmark.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_MODIFY_TEST() throws Exception {
        //given
        LandmarkModifyRequest landmarkModifyRequest = LandmarkModifyRequest.builder()
                .name("Test")
                .description("description")
                .address("address")
                .homepage("homepage")
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        String req = objectMapper.writeValueAsString(landmarkModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/landmarkModify/" + landmark.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_LIST_TEST() throws Exception {
        //given
        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(landmark);

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

        //Response
        LandmarkListVo landmarkListVo = new LandmarkListVo(landmarkList, landmarkHashTagListList);

        String response = objectMapper.writeValueAsString(landmarkListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkList")
                .param("worldCountryCityRegionId", String.valueOf(worldCountryCityRegion.getId()))
                .param("worldCountryCityId", String.valueOf(worldCountryCity.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_TEST() throws Exception {
        //given
        List<LandmarkImg> landmarkImgList = new ArrayList<>();
        landmarkImgList.add(landmarkImg);

        LandmarkVo landmarkVo = new LandmarkVo(landmark, landmarkImgList);

        String response = objectMapper.writeValueAsString(landmarkVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmark")
                .param("landmarkId", String.valueOf(landmark.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_ALL_LIST_TEST() throws Exception {
        //given
        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(landmark);

        LandmarkAllListVo landmarkAllListVo = new LandmarkAllListVo(landmarkList);

        String response = objectMapper.writeValueAsString(landmarkAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void LANDMARK_ALL_COUNT_TEST() throws Exception {
        //given
        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(landmark);

        LandmarkAllCountVo landmarkAllCountVo = new LandmarkAllCountVo(Long.valueOf(landmarkList.size()));

        String response = objectMapper.writeValueAsString(landmarkAllCountVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/landmarkAllCount"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}