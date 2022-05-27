package com.tripfestival.controller.event;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.tripfestival.request.event.EventModifyRequest;
import com.tripfestival.request.event.EventProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventAllListVo;
import com.tripfestival.vo.event.EventListVo;
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

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest extends BaseControllerTest {

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
    }

    @Test
    void EVENT_PROCESS_TEST() throws Exception {
        //given
        EventProcessRequest eventProcessRequest = EventProcessRequest.builder()
                .name("test")
                .startDate("2022-01-01")
                .endDate("2022-01-02")
                .description("test")
                .address("test")
                .visitor(100)
                .inout(1)
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .eventCategoryId(eventCategory.getId())
                .eventSeasonId(eventSeason.getId())
                .build();


        String value = objectMapper.writeValueAsString(eventProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/eventProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void EVENT_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventRemove/" + event.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }


    @Test
    void EVENT_MODIFY_TEST() throws Exception {
        //given
        EventModifyRequest eventModifyRequest = EventModifyRequest.builder().name("hi").build();
        String value = objectMapper.writeValueAsString(eventModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/eventModify/" + event.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(value));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_LIST_TEST() throws Exception {
        //given
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(event2);


        // params
        String worldCountryCityId = String.valueOf(worldCountryCity.getId());
        String worldCountryCityRegionId = String.valueOf(worldCountryCityRegion.getId());

        // Response
        List<List<EventHashTag>> eventHashTagListList = new ArrayList<>();

        for (Event event : eventList) {
            List<EventHashTag> eventHashTagList = eventHashTagRepository.findByEvent(event);

            if(eventHashTagList.size() == 0) {
                eventHashTagListList.add(new ArrayList<EventHashTag>());
            }else {
                eventHashTagListList.add(eventHashTagList);
            }
        }

        EventListVo eventListVo = new EventListVo(eventList, eventHashTagListList);


        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventList")
                .param("worldCountryCityId", worldCountryCityId)
                .param("worldCountryCityRegionId", worldCountryCityRegionId));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventListVo)))
                .andDo(print());
    }


    @Test
    void EVENT_ALL_LIST_TEST() throws Exception {
        //given
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(event2);

        EventAllListVo eventAllListVo = new EventAllListVo(eventList);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/eventAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(eventAllListVo)))
                .andDo(print());
    }


}