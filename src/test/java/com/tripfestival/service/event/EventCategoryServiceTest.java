package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventCategoryListDto;
import com.tripfestival.dto.event.EventCategoryNameModifyDto;
import com.tripfestival.dto.event.EventCategoryProcessDto;
import com.tripfestival.exception.event.EventCategoryNotFoundException;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.event.EventCategoryAllListVo;
import com.tripfestival.vo.event.EventCategoryListVo;
import org.junit.jupiter.api.Assertions;
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
class EventCategoryServiceTest {

    @Autowired
    EventCategoryService eventCategoryService;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    EventRepository eventRepository;


    @Test
    void EVENT_CATEGORY_INSERT_TEST() {
        //given
        EventCategoryProcessDto req = new EventCategoryProcessDto(FileTestUtil.getMockMultipartFile(), "test");

        //when
        eventCategoryService.eventCategoryInsert(req);

        //then
        Assertions.assertNotNull(eventCategoryRepository.findByName("test").get());
    }

    @Test
    void EVENT_CATEGORY_DELETE_TEST() {
        //given
        EventCategory eventCategory = EventCategory.builder()
                .name("test")
                .img("test.png")
                .build();

        eventCategoryRepository.save(eventCategory);

        //when
        eventCategoryService.eventCategoryDelete(eventCategory.getId());

        //then
        Assertions.assertFalse(eventCategoryRepository.existsById(eventCategory.getId()));
    }

    @Test
    void EVENT_CATEGORY_NAME_MODIFY_TEST() {
        //given
        String CHANGE_NAME = "testChange";

        EventCategory eventCategory = EventCategory.builder()
                .name("test")
                .build();

        eventCategoryRepository.save(eventCategory);

        EventCategoryNameModifyDto eventCategoryNameModifyDto = EventCategoryNameModifyDto.builder()
                .eventCategoryId(eventCategory.getId())
                .name(CHANGE_NAME)
                .build();

        //when
        eventCategoryService.eventCategoryNameModify(eventCategoryNameModifyDto);


        //then
        Assertions.assertEquals(CHANGE_NAME, eventCategoryRepository.findByName(eventCategory.getName()).get().getName());
    }

    @Test
    void EVENT_CATEGORY_LIST_SELECT_TEST() {
        //given
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

        // Create Event List
        List<Event> eventList = new ArrayList<>();

        Event event = Event.builder()
                .name("event")
                .worldCountryCityRegion(worldCountryCityRegion)
                .eventCategory(eventCategory)
                .build();
        eventRepository.save(event);

        // Insert Event
        eventList.add(event);

        EventCategoryListDto eventCategoryListDto = EventCategoryListDto.builder()
            .eventCategoryId(eventCategory.getId())
            .worldCountryCityId(worldCountryCity.getId())
            .worldCountryCityRegionId(worldCountryCityRegion.getId())
            .build();

        EventCategoryListVo eventCategoryListVo1 = new EventCategoryListVo(eventList);

        //when
        EventCategoryListVo eventCategoryListVo2 = eventCategoryService.eventCategoryListSelect(eventCategoryListDto);

        //then
        assertThat(eventCategoryListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventCategoryListVo2);
    }


    @Test
    void EVENT_CATEGORY_ALL_SELECT_TEST() {
        //given
        List<EventCategory> eventCategoryList = new ArrayList<>();

        EventCategory eventCategory = EventCategory.builder()
                .name("eventCategory")
                .build();
        eventCategoryRepository.save(eventCategory);

        eventCategoryList.add(eventCategory);

        EventCategoryAllListVo eventCategoryAllListVo1 = new EventCategoryAllListVo(eventCategoryList);

        //when
        EventCategoryAllListVo eventCategoryAllListVo2 = eventCategoryService.eventCategoryAllSelect();

        //then
        assertThat(eventCategoryAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventCategoryAllListVo2);
    }

    @Test
    void EVENT_CATEGORY_EVENT_CATEGORY_EXCEPTION_TEST() {
        Assertions.assertThrows(EventCategoryNotFoundException.class, () -> {
            eventCategoryService.eventCategoryDelete(1L);
        });
    }
}