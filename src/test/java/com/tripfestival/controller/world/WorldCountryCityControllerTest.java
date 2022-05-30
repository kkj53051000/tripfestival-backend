package com.tripfestival.controller.world;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryCityNameModifyRequest;
import com.tripfestival.request.world.WorldCountryCityProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryCityNameListVo;
import com.tripfestival.vo.world.WorldCountryCityNameVo;
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
class WorldCountryCityControllerTest extends BaseControllerTest {

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    WorldCountry worldCountry;
    WorldCountryCity worldCountryCity;

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
    }


    @Test
    void WORLD_COUNTRY_CITY_PROCESS_TEST() throws Exception {
        //given
        WorldCountryCityProcessRequest worldCountryCityProcessRequest = WorldCountryCityProcessRequest.builder()
                .name("city")
                .worldCountryId(worldCountry.getId())
                .areaCode(0)
                .build();

        String value = objectMapper.writeValueAsString(worldCountryCityProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/worldCountryCityProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/worldCountryCityRemove/" + worldCountryCity.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_NAME_MODIFY_TEST() throws Exception {
        //given
        WorldCountryCityNameModifyRequest worldCountryCityNameModifyRequest = WorldCountryCityNameModifyRequest.builder()
                .name("cityTwo")
                .build();

        String req = objectMapper.writeValueAsString(worldCountryCityNameModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/worldCountryCityNameModify/" + worldCountryCity.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_NAME_LIST_TEST() throws Exception {
        //given
        List<WorldCountryCity> worldCountryCityList = new ArrayList<>();
        worldCountryCityList.add(worldCountryCity);

        WorldCountryCityNameListVo worldCountryCityNameListVo = new WorldCountryCityNameListVo(worldCountryCityList);

        String response = objectMapper.writeValueAsString(worldCountryCityNameListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/worldCountryCityNameList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_CITY_NAME_TEST() throws Exception {
        //given
        WorldCountryCityNameVo worldCountryCityNameVo = WorldCountryCityNameVo.builder()
                .worldCountryCityName(worldCountryCity.getName())
                .build();

        String response = objectMapper.writeValueAsString(worldCountryCityNameVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/worldCountryCityName/" + worldCountryCity.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}