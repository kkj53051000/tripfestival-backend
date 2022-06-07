package com.tripfestival.service.event;

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
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventHashTagAllListVo;
import com.tripfestival.vo.event.EventHashTagListVo;
import io.jsonwebtoken.lang.Assert;
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
class EventHashTagServiceTest {

    @Autowired
    EventHashTagService eventHashTagService;

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

    Event event;
    EventHashTag eventHashTag;

    ResponseVo successResponseVo = new ResponseVo(Response.SUCCESS, null);

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

        eventHashTag = EventHashTag.builder()
                .name("eventHashTag")
                .event(event)
                .build();
        eventHashTagRepository.save(eventHashTag);
    }

    @Test
    void EVENT_HASH_TAG_INSERT_TEST() {
        //given
        EventHashTagProcessRequest eventHashTagProcessRequest = EventHashTagProcessRequest.builder()
                .name("test")
                .eventId(event.getId())
                .build();

        //when
        ResponseVo responseVo = eventHashTagService.eventHashTagInsert(eventHashTagProcessRequest);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);
    }

    @Test
    void EVENT_HASH_TAG_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventHashTagService.eventHashTagDelete(eventHashTag.getId());

        //then
        Assertions.assertEquals(successResponseVo, responseVo);
    }

    @Test
    void EVENT_HASH_TAG_LIST_SELECT_TEST() {
        //given
        List<EventHashTag> eventHashTagList = new ArrayList<>();
        eventHashTagList.add(eventHashTag);

        EventHashTagListVo eventHashTagListVo1 = new EventHashTagListVo(eventHashTagList);

        //when
        EventHashTagListVo eventHashTagListVo2 = eventHashTagService.eventHashTagListSelect(event.getId());

        //then
        assertThat(eventHashTagListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventHashTagListVo2);
    }

    @Test
    void EVENT_HASH_TAG_ALL_LIST_SELECT_TEST() {
        //given
        List<EventHashTag> eventHashTagList = new ArrayList<>();
        eventHashTagList.add(eventHashTag);

        EventHashTagAllListVo eventHashTagAllListVo1 = new EventHashTagAllListVo(eventHashTagList);

        //when
        EventHashTagAllListVo eventHashTagAllListVo2 = eventHashTagService.eventHashTagListAllSelect();

        //then
        assertThat(eventHashTagAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventHashTagAllListVo2);
    }
}