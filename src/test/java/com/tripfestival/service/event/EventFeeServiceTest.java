package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventFee;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventFeeModifyDto;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventFeeRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventFeeAllListVo;
import com.tripfestival.vo.event.EventFeeListVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EventFeeServiceTest {

    @Autowired
    EventFeeService eventFeeService;

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
    EventFeeRepository eventFeeRepository;


    Event event;
    EventFee eventFee;


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
                .worldCountryCityRegion(worldCountryCityRegion)
                .eventCategory(eventCategory)
                .eventSeason(eventSeason)
                .build();
        eventRepository.save(event);

        eventFee = EventFee.builder()
                .title("eventFee")
                .event(event)
                .build();
        eventFeeRepository.save(eventFee);
    }


    @Test
    void EVENT_FEE_INSERT_TEST() {
        //given
        EventFeeProcessRequest eventFeeProcessRequest = EventFeeProcessRequest.builder()
                .title("test")
                .price(1000)
                .eventId(event.getId())
                .build();

        //when
        ResponseVo responseVo = eventFeeService.eventFeeInsert(eventFeeProcessRequest);

        //then
        Assertions.assertEquals(responseVo, new ResponseVo(Response.SUCCESS, null));
    }

    @Test
    void EVENT_FEE_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventFeeService.eventFeeDelete(eventFee.getId());

        //then
        Assertions.assertEquals(responseVo, new ResponseVo(Response.SUCCESS, null));
    }

    @Test
    void EVENT_FEE_ALERT_TEST() {
        //given
        EventFeeModifyDto eventFeeModifyDto = EventFeeModifyDto.builder()
                .title("eventFeeChange")
                .price(10000)
                .eventFeeId(eventFee.getId())
                .build();

        //when
        ResponseVo responseVo = eventFeeService.eventFeeAlert(eventFeeModifyDto);

        //then
        Assertions.assertEquals(responseVo, new ResponseVo(Response.SUCCESS, null));
    }

    @Test
    void EVENT_FEE_LIST_SELECT_TEST() {
        //given
        List<EventFee> eventFeeList = new ArrayList<>();
        eventFeeList.add(eventFee);

        EventFeeListVo eventFeeListVo1 = new EventFeeListVo(eventFeeList);

        //when
        EventFeeListVo eventFeeListVo2 = eventFeeService.eventFeeListSelect(event.getId());

        //then
        assertThat(eventFeeListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventFeeListVo2);
    }

    @Test
    void EVENT_FEE_ALL_LIST_SELECT_TEST() {
        //given
        List<EventFee> eventFeeList = new ArrayList<>();
        eventFeeList.add(eventFee);

        EventFeeAllListVo eventFeeAllListVo1 = new EventFeeAllListVo(eventFeeList);

        //when
        EventFeeAllListVo eventFeeAllListVo2 = eventFeeService.eventFeeAllListSelect();

        //then
        assertThat(eventFeeAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventFeeAllListVo2);
    }
}