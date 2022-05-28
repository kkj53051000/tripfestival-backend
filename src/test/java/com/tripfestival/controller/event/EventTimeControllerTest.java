package com.tripfestival.controller.event;

import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.event.EventTime;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.event.EventTimeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventTimeModifyRequest;
import com.tripfestival.request.event.EventTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventTimeAllListVo;
import com.tripfestival.vo.event.EventTimeListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventTimeControllerTest extends BaseControllerTest {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    EventSeasonRepository eventSeasonRepository;

    @Autowired
    EventTimeRepository eventTimeRepository;

    Event event;
    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    EventCategory eventCategory;
    EventSeason eventSeason;
    EventTime eventTime;

    @BeforeEach
    void setup() {
        WorldCountry worldCountry = WorldCountry.builder()
                .name("대한민국")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("서울")
                .worldCountry(worldCountry)
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("관악구")
                .worldCountryCity(worldCountryCity)
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        eventCategory = EventCategory.builder()
                .name("test")
                .build();
        eventCategoryRepository.save(eventCategory);

        eventSeason = EventSeason.builder()
                .name("test")
                .build();
        eventSeasonRepository.save(eventSeason);

        event = Event.builder()
                .name("event1")
                .img("test.jpg")
                .description("test")
                .address("test")
                .visitor(1)
                .inout(true)
                .worldCountryCityRegion(worldCountryCityRegion)
                .eventCategory(eventCategory)
                .eventSeason(eventSeason)
                .build();
        eventRepository.save(event);



        eventTime = EventTime.builder()
                .title("test")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .event(event)
                .build();

        eventTimeRepository.save(eventTime);
    }

    @Test
    void EVENT_TIME_PROCESS_TEST() throws Exception {
        //given
        EventTimeProcessRequest eventTimeProcessRequest = EventTimeProcessRequest.builder()
                .title("test")
                .startTime(String.valueOf(LocalDateTime.now()))
                .endTime(String.valueOf(LocalDateTime.now()))
                .eventId(event.getId())
                .build();

        String req = objectMapper.writeValueAsString(eventTimeProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventTimeProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_TIME_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventTimeRemove/" + eventTime.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_TIME_MODIFY_TEST() throws Exception {
        //given
        EventTimeModifyRequest eventTimeModifyRequest = EventTimeModifyRequest.builder()
                .title("testModify")
                .startTime(LocalDateTime.now().toString())
                .endTime(LocalDateTime.now().toString())
                .build();

        String req = objectMapper.writeValueAsString(eventTimeModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventTimeModify/" + eventTime.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_TIME_LIST_TEST() throws Exception {
        //given
        List<EventTime> eventTimeList = new ArrayList<>();
        eventTimeList.add(eventTime);

        String response  = objectMapper.writeValueAsString(new EventTimeListVo(eventTimeList));

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventTimeList")
                .param("eventId", String.valueOf(event.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_TIME_ALL_LIST_TEST() throws Exception {
        //given
        List<EventTime> eventTimeList = new ArrayList<>();
        eventTimeList.add(eventTime);

        EventTimeAllListVo eventTimeAllListVo = new EventTimeAllListVo(eventTimeList);

        String response = objectMapper.writeValueAsString(eventTimeAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventTimeAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}