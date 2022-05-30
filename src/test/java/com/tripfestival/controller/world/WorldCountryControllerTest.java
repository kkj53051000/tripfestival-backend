package com.tripfestival.controller.world;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryModifyRequest;
import com.tripfestival.request.world.WorldCountryProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryNameListVo;
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
class WorldCountryControllerTest extends BaseControllerTest {

    @Autowired
    WorldCountryRepository worldCountryRepository;

    WorldCountry worldCountry;

    @BeforeEach
    void setup() {
        worldCountry = WorldCountry.builder()
                .name("country")
                .build();
        worldCountryRepository.save(worldCountry);
    }


    @Test
    void WORLD_COUNTRY_PROCESS_TEST() throws Exception {
        //given
        WorldCountryProcessRequest worldCountryProcessRequest = WorldCountryProcessRequest.builder()
                .name("name")
                .currency("currency")
                .capital("capital")
                .exchangeRatio("exchangeRatio")
                .build();

        String value = objectMapper.writeValueAsString(worldCountryProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/worldCountryProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/worldCountryRemove/" + worldCountry.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_MODIFY_TEST() throws Exception {
        //given
        WorldCountryModifyRequest worldCountryModifyRequest = WorldCountryModifyRequest.builder()
                .name("name2")
                .capital("capital2")
                .currency("currency2")
                .exchangeRatio("exchangeRatio2")
                .build();

        String req = objectMapper.writeValueAsString(worldCountryModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/worldCountryModify/" + worldCountry.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void WORLD_COUNTRY_NAME_LIST_TEST() throws Exception {
        //given
        List<WorldCountry> worldCountryList = new ArrayList<>();
        worldCountryList.add(worldCountry);

        WorldCountryNameListVo worldCountryNameListVo = new WorldCountryNameListVo(worldCountryList);

        String response = objectMapper.writeValueAsString(worldCountryNameListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/worldCountryNameList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}