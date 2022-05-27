package com.tripfestival.controller.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.request.event.EventCategoryModifyRequest;
import com.tripfestival.request.event.EventCategoryProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventCategoryAllListVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class EventCategoryControllerTest extends BaseControllerTest{
    @MockBean
    FileService fileService;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    EventCategory eventCategory;
    EventCategory eventCategory2;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.png");

        eventCategory = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory);
        eventCategory2 = new EventCategory("test", "test.png");
        eventCategoryRepository.save(eventCategory2);
    }

    @Test
    void EVENT_CATEGORY_PROCESS_TEST() throws Exception {
        //given
        EventCategoryProcessRequest req = new EventCategoryProcessRequest("test");

        String value = objectMapper.writeValueAsString(req);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/eventCategoryProcess")
                    .file(FileTestUtil.getMockMultipartFile())
                    .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))));
    }

    @Test
    void EVENT_CATEGORY_DELETE_TEST() throws Exception {
        //given setup()


        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventCategoryRemove/" + eventCategory.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_CATEGORY_NAME_MODIFY_TEST() throws Exception {
        //given
        EventCategoryModifyRequest req = new EventCategoryModifyRequest("test2");
        String jsonReq = objectMapper.writeValueAsString(req);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventCategoryNameModify/" + eventCategory.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReq));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_CATEGORY_IMG_MODIFY_TEST() throws Exception {
        //given
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.png");

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/eventCategoryImgModify/" + eventCategory.getId())
                .file(FileTestUtil.getMockMultipartFile()));

        //then
        resultActions
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_CATEGORY_ALL_LIST() throws Exception {
        // given
        List<EventCategory> eventCategoryList = new ArrayList<>();

        eventCategoryList.add(eventCategory);
        eventCategoryList.add(eventCategory2);

        EventCategoryAllListVo eventCategoryAllListVo = new EventCategoryAllListVo(eventCategoryList);


        // when
        ResultActions resultActions = mockMvc.perform(get("/api/eventCategoryAllList"));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventCategoryAllListVo)))
                .andDo(print());
    }

}