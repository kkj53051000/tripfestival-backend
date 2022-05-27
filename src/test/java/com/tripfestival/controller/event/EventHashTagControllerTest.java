package com.tripfestival.controller.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventHashTag;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventHashTagRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventHashTagProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventHashTagAllListVo;
import com.tripfestival.vo.event.EventHashTagListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventHashTagControllerTest extends BaseControllerTest {

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
    EventHashTagRepository eventHashTagRepository;

    @MockBean
    FileService fileService;

    Event event;
    Event event2;
    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    EventSeason eventSeason;
    EventCategory eventCategory;
    EventHashTag eventHashTag;
    EventHashTag eventHashTag2;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.jpg");

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

        event2 = Event.builder()
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
        eventRepository.save(event2);

        eventHashTag = EventHashTag.builder()
                .name("test")
                .event(event)
                .build();
        eventHashTagRepository.save(eventHashTag);

        eventHashTag2 = EventHashTag.builder()
                .name("test")
                .event(event)
                .build();
        eventHashTagRepository.save(eventHashTag2);
    }

    @Test
    void EVENT_HASH_TAG_PROCESS_TEST() throws Exception {
        //given
        EventHashTagProcessRequest eventHashTagProcessRequest = EventHashTagProcessRequest.builder()
                .name("test")
                .eventId(event.getId())
                .build();

        String req = objectMapper.writeValueAsString(eventHashTagProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventHashTagProcess")
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_HASH_TAG_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventHashTagRemove/" + eventHashTag.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_HASH_TAG_LIST_TEST() throws Exception {
        //given
        List<EventHashTag> eventHashTagList = new ArrayList<>();

        eventHashTagList.add(eventHashTag);
        eventHashTagList.add(eventHashTag2);

        EventHashTagListVo eventHashTagListVo = new EventHashTagListVo(eventHashTagList);
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventHashTagList")
                .param("eventId", String.valueOf(event.getId())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventHashTagListVo)))
                .andDo(print());
    }

    @Test
    void EVENT_HASH_TAG_ALL_LIST_TEST() throws Exception {
        //given
        List<EventHashTag> eventHashTagList = new ArrayList<>();

        eventHashTagList.add(eventHashTag);
        eventHashTagList.add(eventHashTag2);

        EventHashTagAllListVo eventHashTagAllListVo = new EventHashTagAllListVo(eventHashTagList);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventHashTagAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventHashTagAllListVo)))
                .andDo(print());
    }
}