package com.tripfestival.controller.hotspot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.repository.hotspot.HotspotTypeRepository;
import com.tripfestival.request.hotspot.HotspotTypeNameModifyRequest;
import com.tripfestival.request.hotspot.HotspotTypeProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotTypeAllListVo;
import com.tripfestival.vo.hotspot.HotspotTypeNameVo;
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
class HotspotTypeControllerTest extends BaseControllerTest {

    @Autowired
    HotspotTypeRepository hotspotTypeRepository;

    HotspotType hotspotType;

    @BeforeEach
    void setup() {
        hotspotType = HotspotType.builder()
                .name("test")
                .build();
        hotspotTypeRepository.save(hotspotType);
    }

    @Test
    void HOTSPOT_TYPE_PROCESS_TEST() throws Exception {
        //given
        HotspotTypeProcessRequest hotspotTypeProcessRequest = HotspotTypeProcessRequest.builder()
                .name("test")
                .build();

        String value = objectMapper.writeValueAsString(hotspotTypeProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/hotspotTypeProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_TYPE_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api//admin/hotspotTypeRemove/" + hotspotType.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_TYPE_NAME_MODIFY_TEST() throws Exception {
        //given
        HotspotTypeNameModifyRequest hotspotTypeNameModifyRequest = HotspotTypeNameModifyRequest.builder()
                .name("Test")
                .build();

        String req = objectMapper.writeValueAsString(hotspotTypeNameModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/hotspotTypeNameModify/" + hotspotType.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_TYPE_ALL_LIST_TEST() throws Exception {
        //given
        List<HotspotType> hotspotTypeList = new ArrayList<>();
        hotspotTypeList.add(hotspotType);

        HotspotTypeAllListVo hotspotTypeAllListVo = new HotspotTypeAllListVo(hotspotTypeList);

        String response = objectMapper.writeValueAsString(hotspotTypeAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotspotTypeAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void HOTSPOT_TYPE_NAME_TEST() throws Exception {
        //given
        HotspotTypeNameVo hotspotTypeNameVo = HotspotTypeNameVo.builder()
                .hotSpotTypeName(hotspotType.getName())
                .build();

        String response = objectMapper.writeValueAsString(hotspotTypeNameVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/hotspotTypeName/" + hotspotType.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}