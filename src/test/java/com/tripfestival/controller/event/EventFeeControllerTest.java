package com.tripfestival.controller.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventFee;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.event.EventFeeRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.request.event.EventFeeModifyRequest;
import com.tripfestival.request.event.EventFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventFeeAllListVo;
import com.tripfestival.vo.event.EventFeeListVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventFeeControllerTest extends BaseControllerTest {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventFeeRepository eventFeeRepository;

    private Event event;
    private EventFee eventFee;
    private EventFee eventFee2;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setName("event");
        eventRepository.save(event);

        eventFee = EventFee.builder()
                .title("one")
                .price(1)
                .event(event)
                .build();
        eventFeeRepository.save(eventFee);

        eventFee2 = EventFee.builder()
                .title("two")
                .price(2)
                .event(event)
                .build();
        eventFeeRepository.save(eventFee2);
    }

    @Test
    void EVENT_FEE_PROCESS_TEST() throws Exception {
        //given
        EventFeeProcessRequest eventFeeProcessRequest = EventFeeProcessRequest.builder()
                .eventId(event.getId())
                .build();

        String req = objectMapper.writeValueAsString(eventFeeProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventFeeProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))));
    }

    @Test
    void EVENT_FEE_REMOVE_TEST() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventFeeRemove/" + eventFee.getId()));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))));
    }

    @Test
    void EVENT_FEE_MODIFY_TEST() throws Exception {
        //given
        EventFeeModifyRequest eventFeeModifyRequest = EventFeeModifyRequest.builder()
                .title("test")
                .price(1000)
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventFeeModify/" + eventFee.getId())
                .contentType(jsonMediaType)
                .content(objectMapper.writeValueAsString(eventFeeModifyRequest)));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_FEE_LIST() throws Exception {
        //given
        List<EventFee> eventFeeList = new ArrayList<>();

        eventFeeList.add(eventFee);
        eventFeeList.add(eventFee2);

        // Response Result
        EventFeeListVo eventFeeListVo = new EventFeeListVo(eventFeeList);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventFeeList")
                .accept(jsonMediaType)
                .param("eventId",String.valueOf(event.getId())));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventFeeListVo)))
                .andDo(print());
    }

    @Test
    void EVENT_FEE_ALL_LIST_TEST() throws Exception {
        //given
        List<EventFee> eventFeeList = new ArrayList<>();

        eventFeeList.add(eventFee);
        eventFeeList.add(eventFee2);

        EventFeeAllListVo eventFeeAllListVo = new EventFeeAllListVo(eventFeeList);
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventFeeAllList"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventFeeAllListVo)))
                .andDo(print());
    }
}