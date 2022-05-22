package com.tripfestival.controller.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventFee;
import com.tripfestival.repository.event.EventFeeRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.request.event.EventFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventFeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EventRepository eventRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void EVENT_FEE_PROCESS() throws Exception {
        //given
        Event event = new Event();
        eventRepository.save(event);

        EventFeeProcessRequest eventFeeProcessRequest = EventFeeProcessRequest.builder()
                .title("test")
                .price(1)
                .eventId(event.getId())
                .build();

        String req = objectMapper.writeValueAsString(eventFeeProcessRequest);

        //when
        //then
        this.mockMvc.perform(post("/api/admin/eventFeeProcess")
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());

    }
}