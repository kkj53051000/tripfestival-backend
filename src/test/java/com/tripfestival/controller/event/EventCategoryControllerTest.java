package com.tripfestival.controller.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.request.event.EventCategoryModifyRequest;
import com.tripfestival.request.event.EventCategoryProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventCategoryAllListVo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventCategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    FileService fileService;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void EVENT_CATEGORY_PROCESS_TEST() throws Exception {
        //given
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.png");

        EventCategoryProcessRequest req = new EventCategoryProcessRequest("test");

        String value = objectMapper.writeValueAsString(req);

        //when
        //then
        this.mockMvc.perform(multipart("/api/eventCategoryProcess")
                    .file(FileTestUtil.getMockMultipartFile())
                    .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())))
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_CATEGORY_DELETE_TEST() throws Exception {
        //given
        EventCategory eventCategory = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory);

        //when
        //then
        this.mockMvc.perform(post("/api/eventCategoryRemove/" + eventCategory.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_CATEGORY_NAME_MODIFY_TEST() throws Exception {
        //given
        EventCategory eventCategory = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory);

        EventCategoryModifyRequest req = new EventCategoryModifyRequest("test2");
        String jsonReq = objectMapper.writeValueAsString(req);

        //when
        //then
        this.mockMvc.perform(post("/api/eventCategoryNameModify/" + eventCategory.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReq))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_CATEGORY_IMG_MODIFY_TEST() throws Exception {
        //given
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.png");

        EventCategory eventCategory = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory);

        //when then
        this.mockMvc.perform(multipart("/api/eventCategoryImgModify/" + eventCategory.getId())
                .file(FileTestUtil.getMockMultipartFile()))
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_CATEGORY_ALL_LIST() throws Exception {
        // given
        List<EventCategory> eventCategoryList = new ArrayList<>();

        EventCategory eventCategory1 = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory1);

        EventCategory eventCategory2 = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory2);

        eventCategoryList.add(eventCategory1);
        eventCategoryList.add(eventCategory2);

        EventCategoryAllListVo eventCategoryAllListVo = new EventCategoryAllListVo(eventCategoryList);


        // when then
        this.mockMvc.perform(get("/api/eventCategoryAllList"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventCategoryAllListVo)))
                .andDo(print());
    }

}