package com.tripfestival.controller.world;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import com.tripfestival.request.world.WorldCountryCityRegionProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.WorldCountryCityRegionListVo;
import com.tripfestival.vo.world.WorldCountryCityRegionNameVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
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
class WorldCountryCityRegionControllerTest extends BaseControllerTest {

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    WorldCountry worldCountry;
    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;


    @BeforeEach
    void setup() {
        worldCountry = WorldCountry.builder()
                .name("country")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("city")
                .worldCountry(worldCountry)
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion =WorldCountryCityRegion.builder()
                .name("region")
                .worldCountryCity(worldCountryCity)
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_PROCESS_TEST() throws Exception {
        //given
        WorldCountryCityRegionProcessRequest worldCountryCityRegionProcessRequest = WorldCountryCityRegionProcessRequest.builder()
                .name("region")
                .worldCountryCityId(worldCountryCity.getId())
                .build();

        String value = objectMapper.writeValueAsString(worldCountryCityRegionProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/worldCountryCityRegionProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/worldCountryCityRegionRemove/" + worldCountryCityRegion.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_NAME_MODIFY_TEST() throws Exception {
        //given
        WorldCountryCityRegionNameModifyRequest worldCountryCityRegionNameModifyRequest = WorldCountryCityRegionNameModifyRequest.builder()
                .name("change")
                .build();

        String req = objectMapper.writeValueAsString(worldCountryCityRegionNameModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/worldCountryCityRegionNameModify/" + worldCountryCityRegion.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_TEST() throws Exception {
        //given
        List<WorldCountryCityRegion> worldCountryCityRegionList = new ArrayList<>();
        worldCountryCityRegionList.add(worldCountryCityRegion);

        WorldCountryCityRegionListVo worldCountryCityRegionListVo = new WorldCountryCityRegionListVo(worldCountryCityRegionList);

        String response = objectMapper.writeValueAsString(worldCountryCityRegionListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/worldCountryCityRegion")
                .param("worldCountryId", String.valueOf(worldCountry.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_LIST_TEST() throws Exception {
        //given
        List<WorldCountryCityRegion> worldCountryCityRegionList = new ArrayList<>();
        worldCountryCityRegionList.add(worldCountryCityRegion);

        WorldCountryCityRegionListVo worldCountryCityRegionListVo = new WorldCountryCityRegionListVo(worldCountryCityRegionList);

        String response = objectMapper.writeValueAsString(worldCountryCityRegionListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/worldCountryCityRegionList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_NAME_TEST() throws Exception {
        //given
        WorldCountryCityRegionNameVo worldCountryCityRegionNameVo = WorldCountryCityRegionNameVo.builder()
                .worldCountryCityRegionName(worldCountryCityRegion.getName())
                .build();

        String response = objectMapper.writeValueAsString(worldCountryCityRegionNameVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/worldCountryCityRegionName/" + worldCountryCityRegion.getId()));


        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}