package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.event.EventTime;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventTimeModifyDto;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.event.EventTimeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventTimeAllListVo;
import com.tripfestival.vo.event.EventTimeListVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EventTimeServiceTest {

    @Autowired
    EventTimeService eventTimeService;

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
    EventTimeRepository eventTimeRepository;

    private Event event;
    private EventTime eventTime;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    @BeforeEach
    void setup() {
        WorldCountry worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);

        WorldCountryCity worldCountryCity = WorldCountryCity.builder()
                .name("worldCountryCity")
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("worldCountryCityRegion")
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        EventCategory eventCategory = EventCategory.builder()
                .name("eventCategory")
                .build();
        eventCategoryRepository.save(eventCategory);

        EventSeason eventSeason = EventSeason.builder()
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

        eventTime = EventTime.builder()
                .title("eventTime")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .event(event)
                .build();
        eventTimeRepository.save(eventTime);
    }

    @Test
    void EVENT_TIME_INSERT_TEST() {
        //given
        EventTimeProcessRequest eventTimeProcessRequest = EventTimeProcessRequest.builder()
                .title("title")
                .startTime(String.valueOf(LocalDateTime.now()))
                .endTime(String.valueOf(LocalDateTime.now()))
                .eventId(event.getId())
                .build();

        //when
        ResponseVo responseVo = eventTimeService.eventTimeInsert(eventTimeProcessRequest);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);
    }

    @Test
    void EVENT_TIME_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventTimeService.eventTimeDelete(eventTime.getId());

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void EVENT_TIME_ALERT_TEST() {
        //given
        EventTimeModifyDto eventTimeModifyDto = EventTimeModifyDto.builder()
                .eventTimeId(eventTime.getId())
                .title("titleChage")
                .startTime(String.valueOf(LocalDateTime.now()))
                .endTime(String.valueOf(LocalDateTime.now()))
                .build();

        //when
        ResponseVo responseVo = eventTimeService.eventTimeAlert(eventTimeModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void EVENT_TIME_LIST_SELECT_TEST() {
        //given
        List<EventTime> eventTimeList = new ArrayList<>();
        eventTimeList.add(eventTime);

        EventTimeListVo eventTimeListVo1 = new EventTimeListVo(eventTimeList);

        //when
        EventTimeListVo eventTimeListVo2 = eventTimeService.eventTimeListSelect(event.getId());

        //then
        assertThat(eventTimeListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventTimeListVo2);

    }

    @Test
    void EVENT_TIME_ALL_LIST_SELECT() {
        //given
        List<EventTime> eventTimeList = new ArrayList<>();
        eventTimeList.add(eventTime);

        EventTimeAllListVo eventTimeAllListVo1 = new EventTimeAllListVo(eventTimeList);

        //when
        EventTimeAllListVo eventTimeAllListVo2 = eventTimeService.eventTimeAllListSelect();

        //then
        assertThat(eventTimeAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventTimeAllListVo2);

    }
}