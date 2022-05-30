package com.tripfestival.controller.naturehotspot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.request.naturehotspot.NatureHotspotTypeRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotTypeAllListVo;
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
class NatureHotspotTypeControllerTest extends BaseControllerTest {

    @Autowired
    NatureHotspotTypeRepository natureHotspotTypeRepository;


    NatureHotspotType natureHotspotType;

    @BeforeEach
    void setup() {
        natureHotspotType = NatureHotspotType.builder()
                .name("natureHotspotType")
                .build();
        natureHotspotTypeRepository.save(natureHotspotType);
    }

    @Test
    void NATURE_HOTSPOT_TYPE_PROCESS_TEST() throws Exception {
        //given
        NatureHotspotTypeRequest natureHotspotTypeRequest = NatureHotspotTypeRequest.builder()
                .name("natureHotspotType")
                .build();

        String value = objectMapper.writeValueAsString(natureHotspotTypeRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/natureHotspotTypeProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_TYPE_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/natureHotspotTypeRemove/" + natureHotspotType.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_TYPE_ALL_LIST_TEST() throws Exception {
        //given
        List<NatureHotspotType> natureHotspotTypeList = new ArrayList<>();
        natureHotspotTypeList.add(natureHotspotType);

        NatureHotspotTypeAllListVo natureHotspotTypeAllListVo = new NatureHotspotTypeAllListVo(natureHotspotTypeList);

        String response = objectMapper.writeValueAsString(natureHotspotTypeAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/natureHotspotTypeAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}