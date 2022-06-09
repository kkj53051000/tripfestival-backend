package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventHashTag;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventListDto;
import com.tripfestival.dto.event.EventModifyDto;
import com.tripfestival.dto.event.EventProcessDto;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventHashTagRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventAllCountVo;
import com.tripfestival.vo.event.EventAllListVo;
import com.tripfestival.vo.event.EventListVo;
import com.tripfestival.vo.event.EventVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceTest {

    @Autowired
    EventService eventService;

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
    EventRepository eventRepository;

    @Autowired
    EventHashTagRepository eventHashTagRepository;

    @MockBean
    FileService fileService;

    Event event;
    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    EventSeason eventSeason;
    EventCategory eventCategory;

    ResponseVo successResponseVo = new ResponseVo(Response.SUCCESS, null);

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.jpg");

        WorldCountry worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("worldCountryCity")
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("worldCountryCityRegion")
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        eventCategory = EventCategory.builder()
                .name("eventCategory")
                .build();
        eventCategoryRepository.save(eventCategory);

        eventSeason = EventSeason.builder()
                .name("eventSeason")
                .build();
        eventSeasonRepository.save(eventSeason);

        event = Event.builder()
                .name("event")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .img("test.jpg")
                .description("description")
                .address("address")
                .visitor(1)
                .inout(Boolean.FALSE)
                .worldCountryCityRegion(worldCountryCityRegion)
                .eventCategory(eventCategory)
                .eventSeason(eventSeason)
                .build();
        eventRepository.save(event);

        EventHashTag eventHashTag = EventHashTag.builder()
                .name("tag")
                .event(event)
                .build();
        eventHashTagRepository.save(eventHashTag);
    }

    @Test
    void EVENT_INSERT_TEST() {
        //given
        EventProcessDto eventProcessDto = EventProcessDto.builder()
                .file(FileTestUtil.getMockMultipartFile())
                .startDate("2022-01-01")
                .endDate("2022-01-01")
                .name("name")
                .description("description")
                .address("address")
                .visitor(1)
                .inout(1)
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .eventSeasonId(eventSeason.getId())
                .eventCategoryId(eventCategory.getId())
                .build();

        //when
        ResponseVo responseVo = eventService.eventInsert(eventProcessDto);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);

    }

    @Test
    void EVENT_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventService.eventDelete(event.getId());

        //then
        Assertions.assertEquals(successResponseVo, responseVo);
    }

    @Test
    void EVENT_ALERT_TEST() {
        //given
        EventModifyDto eventModifyDto = EventModifyDto.builder()
                .eventId(event.getId())
                .name("name")
                .description("description")
                .address("address")
                .visitor(1)
                .inout(Boolean.TRUE)
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .eventCategoryId(eventCategory.getId())
                .eventSeasonId(eventSeason.getId())
                .build();

        //when
        ResponseVo responseVo = eventService.eventAlert(eventModifyDto);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);
    }

    @Test
    void EVENT_LIST_SELECT_TEST() {
        //given
        EventListDto eventListDto = EventListDto.builder()
                .worldCountryCityId(worldCountryCity.getId())
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        // EventHashTag List
        List<List<EventHashTag>> eventHashTagListList = new ArrayList<>();

        for (Event event : eventList) {
            List<EventHashTag> eventHashTagList = eventHashTagRepository.findByEvent(event);

            if(eventHashTagList.size() == 0) {
                eventHashTagListList.add(new ArrayList<EventHashTag>());
            }else {
                eventHashTagListList.add(eventHashTagList);
            }
        }

        EventListVo eventListVo1 = new EventListVo(eventList, eventHashTagListList);

        //when
        EventListVo eventListVo2 = eventService.eventListSelect(eventListDto);

        //then
        assertThat(eventListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventListVo2);

    }

    @Test
    void EVENT_ALL_LIST_SELECT_TEST() {
        //given
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        EventAllListVo eventAllListVo1 = new EventAllListVo(eventList);

        //when
        EventAllListVo eventAllListVo2 = eventService.eventAllListSelect();

        //then
        assertThat(eventAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventAllListVo2);
    }

    @Test
    void EVENT_SELECT_TEST() {
        //given
        EventVo eventVo1 = new EventVo(event);

        //when
        EventVo eventVo2 = eventService.eventSelect(event.getId());

        //then
        assertThat(eventVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventVo2);
    }

    @Test
    void EVENT_ALL_COUNT_SELECT() {
        //given
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        EventAllCountVo eventAllCountVo1 = EventAllCountVo.builder()
                .count((long)eventList.size())
                .build();

        //when
        EventAllCountVo eventAllCountVo2 = eventService.eventAllCountSelect();

        //then
        assertThat(eventAllCountVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventAllCountVo2);

    }
}