package com.tripfestival.controller.hotsight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.domain.hotsight.HotSightTwo;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.repository.hotsight.HotSightTwoRepository;
import com.tripfestival.request.hotsight.HotSightTwoNameModifyRequest;
import com.tripfestival.request.hotsight.HotSightTwoProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotsight.HotSightTwoAllListVo;
import com.tripfestival.vo.hotsight.HotSightTwoListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.transform.Result;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotSightTwoControllerTest extends BaseControllerTest {

    @Autowired
    HotSightOneRepository hotSightOneRepository;

    @Autowired
    HotSightTwoRepository hotSightTwoRepository;

    HotSightOne hotSightOne;
    HotSightTwo hotSightTwo;

    @BeforeEach
    void setup() {
        hotSightOne = HotSightOne.builder()
                .name("hotSight1")
                .build();
        hotSightOneRepository.save(hotSightOne);

        hotSightTwo = HotSightTwo.builder()
                .name("hotSIght2")
                .build();
        hotSightTwoRepository.save(hotSightTwo);
    }

    @Test
    void HOTSIGHT_TWO_PROCESS_TEST() throws Exception {
        //given
        HotSightTwoProcessRequest hotSightTwoProcessRequest = HotSightTwoProcessRequest.builder()
                .hotSightOneId(hotSightOne.getId())
                .build();

        String value = objectMapper.writeValueAsString(hotSightTwoProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/hotSightTwoProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void HOTSIGHT_TWO_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightTwoRemove/" + hotSightTwo.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void HOTSIGHT_TWO_NAME_MODIFY() throws Exception {
        //given
        HotSightTwoNameModifyRequest hotSightTwoNameModifyRequest = HotSightTwoNameModifyRequest.builder()
                .name("test")
                .build();

        String req = objectMapper.writeValueAsString(hotSightTwoNameModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightTwoNameModify/" + hotSightTwo.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void HOTSIGHT_TWO_IMG_MODIFY() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/hotSightTwoImgModify/" + hotSightTwo.getId())
                .file(FileTestUtil.getMockMultipartFile()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    //    XXX FAILURE XXX
    @Test
    void HOT_SIGHT_TWO_LIST_TEST() throws Exception {
        //given
        List<HotSightTwo> hotSightTwoList = new ArrayList<>();
        hotSightTwoList.add(hotSightTwo);

        HotSightTwoListVo hotSightTwoListVo = new HotSightTwoListVo(hotSightTwoList);

        String response = objectMapper.writeValueAsString(hotSightTwoListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotSightTwoList")
                .param("hotSightOneId", String.valueOf(hotSightOne.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(print());
    }

    @Test
    void HOT_SIGHT_TWO_ALL_LIST_TEST() throws Exception {
        //given
        List<HotSightTwo> hotSightTwoList = new ArrayList<>();
        hotSightTwoList.add(hotSightTwo);

        HotSightTwoAllListVo hotSightTwoAllListVo = new HotSightTwoAllListVo(hotSightTwoList);

        String response = objectMapper.writeValueAsString(hotSightTwoAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotSightTwoAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(print());
    }
}