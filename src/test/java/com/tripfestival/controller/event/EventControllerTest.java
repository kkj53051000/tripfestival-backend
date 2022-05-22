package com.tripfestival.controller.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventModifyRequest;
import com.tripfestival.request.event.EventProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

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

    ObjectMapper objectMapper = new ObjectMapper();

    EventProcessRequest eventProcessRequest;

    @BeforeEach
    void eventInit() {
        // EventProcessRequest
        WorldCountry worldCountry = WorldCountry.builder().build();
        worldCountryRepository.save(worldCountry);

        WorldCountryCity worldCountryCity = WorldCountryCity.builder().build();
        worldCountryCityRepository.save(worldCountryCity);

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder().build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        EventCategory eventCategory = EventCategory.builder().build();
        eventCategoryRepository.save(eventCategory);

        EventSeason eventSeason = EventSeason.builder().build();
        eventSeasonRepository.save(eventSeason);

        this.eventProcessRequest = EventProcessRequest.builder()
                .name("test")
                .description("test")
                .address("test")
                .visitor(1)
                .inout(1)
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .eventCategoryId(eventCategory.getId())
                .eventSeasonId(eventSeason.getId())
                .build();
    }

    @Test
    void EVENT_PROCESS_TEST() throws Exception {
        // given
        String value = objectMapper.writeValueAsString(eventProcessRequest);

        //when
        //then
        this.mockMvc.perform(multipart("/api/admin/eventProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())))
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_REMOVE_TEST() throws Exception {
        //given
        Event event = Event.builder().build();
        eventRepository.save(event);


        //when
        //then
        this.mockMvc.perform(post("/api/admin/eventRemove/" + event.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }


    @Test
    void EVENT_MODIFY_TEST() throws Exception {
        //given
        Event event = Event.builder().name("test").build();
        eventRepository.save(event);

        EventModifyRequest eventModifyRequest = EventModifyRequest.builder().name("hi").build();
        String value = objectMapper.writeValueAsString(eventModifyRequest);

        //when
        //then
        this.mockMvc.perform(post("/api/admin/eventModify/" + event.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(value))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_LIST_TEST() throws Exception {
        //given
        WorldCountry worldCountry = WorldCountry.builder().build();
        worldCountryRepository.save(worldCountry);

        WorldCountryCity worldCountryCity = WorldCountryCity.builder().build();
        worldCountryCityRepository.save(worldCountryCity);

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder().build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        EventCategory eventCategory = EventCategory.builder().build();
        eventCategoryRepository.save(eventCategory);

        EventSeason eventSeason = EventSeason.builder().build();
        eventSeasonRepository.save(eventSeason);

        Event event1 = Event.builder()
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

        Event event2 = Event.builder()
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

        eventRepository.save(event1);
        eventRepository.save(event2);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);


        // params
        String worldCountryCityId = String.valueOf(worldCountryCity.getId());
        String worldCountryCityRegionId = String.valueOf(worldCountryCityRegion.getId());

        // Response
        EventListVo eventListVo = new EventListVo(eventList);
        String response = objectMapper.writeValueAsString(eventList);


        //when
        //then
        this.mockMvc.perform(get("/api/eventList")
                .param("worldCountryCityId", worldCountryCityId)
                .param("worldCountryCityRegionId", worldCountryCityRegionId))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventListVo)))
                .andDo(print());
    }

    @Test
    void EVENT_ALL_LIST_TEST() {
        //given
        //when
        //then


        // 위에 문제 해결하고 해야함.
    }


}