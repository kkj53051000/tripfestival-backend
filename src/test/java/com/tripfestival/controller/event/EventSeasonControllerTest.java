package com.tripfestival.controller.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.request.event.EventSeasonNameModifyRequest;
import com.tripfestival.request.event.EventSeasonProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventSeasonControllerTest extends BaseControllerTest {

    @Autowired
    FileService fileService;

    @Autowired
    EventSeasonRepository eventSeasonRepository;

    EventSeason eventSeason;

    @BeforeEach
    void setup() {
        FileService fileServiceMock = Mockito.mock(FileService.class);
        Mockito.when(fileServiceMock.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.jpg");

        eventSeason = EventSeason.builder()
                .name("test")
                .img("test.png")
                .build();
        eventSeasonRepository.save(eventSeason);
    }

    @Test
    void EVENT_SEASON_PROCESS_TEST() throws Exception {
        //given
        EventSeasonProcessRequest eventSeasonProcessRequest = EventSeasonProcessRequest.builder()
                .name("test")
                .build();

        String value = objectMapper.writeValueAsString(eventSeasonProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/eventSeasonProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_SEASON_REMOVE_TEST() throws Exception {
        //given setup()


        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventSeasonRemove/" + eventSeason.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_SEASON_NAME_MODIFY() throws Exception {
        //given
        EventSeasonNameModifyRequest eventSeasonNameModifyRequest = EventSeasonNameModifyRequest.builder()
                .name("testModify")
                .build();

        String req = objectMapper.writeValueAsString(eventSeasonNameModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventSeasonNameModify/" + eventSeason.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }
}