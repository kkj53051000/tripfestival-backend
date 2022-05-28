package com.tripfestival.controller.hotsight;

import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.request.hotsight.HotSightOneNameModifyRequest;
import com.tripfestival.request.hotsight.HotSightOneProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotsight.HotSightOneListVo;
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
class HotSightOneControllerTest extends BaseControllerTest {

    @Autowired
    HotSightOneRepository hotSightOneRepository;

    HotSightOne hotSightOne;

    @BeforeEach
    void setup() {
        hotSightOne = HotSightOne.builder()
                .name("hotSight1")
                .build();
        hotSightOneRepository.save(hotSightOne);
    }

    @Test
    void HOTSIGHT_ONE_PROCESS_TEST() throws Exception {
        //given
        HotSightOneProcessRequest hotSightOneProcessRequest = HotSightOneProcessRequest.builder()
                .name("test")
                .build();

        String value = objectMapper.writeValueAsString(hotSightOneProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/hotSightOneProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_ONE_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightOneRemove/" + hotSightOne.getId()));

        //test
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_ONE_NAME_MODIFY_TEST() throws Exception {
        //given
        HotSightOneNameModifyRequest hotSightOneNameModifyRequest = HotSightOneNameModifyRequest.builder()
                .name("test")
                .build();

        String req = objectMapper.writeValueAsString(hotSightOneNameModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotSightOneNameModify/" + hotSightOne.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_ONE_IMG_MODIFY_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/hotSightOneImgModify/" + hotSightOne.getId())
                .file(FileTestUtil.getMockMultipartFile()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSIGHT_ONE_ALL_LIST_TEST() throws Exception {
        //given
        List<HotSightOne> hotSightOneList = new ArrayList<>();
        hotSightOneList.add(hotSightOne);

        HotSightOneListVo hotSightOneListVo = new HotSightOneListVo(hotSightOneList);

        String response = objectMapper.writeValueAsString(hotSightOneListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotSightOneAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}