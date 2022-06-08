package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventSeasonImgModifyDto;
import com.tripfestival.dto.event.EventSeasonListDto;
import com.tripfestival.dto.event.EventSeasonNameModifyDto;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventSeasonProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventListVo;
import com.tripfestival.vo.event.EventSeasonListVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EventSeasonServiceTest {

    @Autowired
    EventSeasonService eventSeasonService;

    @Autowired
    EventSeasonRepository eventSeasonRepository;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    EventRepository eventRepository;

    @MockBean
    FileService fileService;


    ResponseVo successResponseVo = new ResponseVo(Response.SUCCESS, null);

    EventSeason eventSeason;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.jpg");

        eventSeason = EventSeason.builder()
                .name("eventSeason")
                .img("test.jpg")
                .build();
        eventSeasonRepository.save(eventSeason);


    }

    @Test
    void EVENT_SEASON_INSERT_TEST() {
        //given
        EventSeasonProcessRequest eventSeasonProcessRequest = EventSeasonProcessRequest.builder()
                .name("EventSeason")
                .build();

        //when
        ResponseVo responseVo = eventSeasonService.eventSeasonInsert(FileTestUtil.getMockMultipartFile(), eventSeasonProcessRequest);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);

    }

    @Test
    void EVENT_SEASON_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventSeasonService.eventSeasonDelete(eventSeason.getId());

        //then
        Assertions.assertEquals(successResponseVo, responseVo);

    }

    @Test
    void EVENT_SEASON_NAME_ALERT_TEST() {
        //given
        EventSeasonNameModifyDto eventSeasonNameModifyDto = EventSeasonNameModifyDto.builder()
                .name("eventSeasonChange")
                .eventSeasonId(eventSeason.getId())
                .build();

        //when
        ResponseVo responseVo = eventSeasonService.eventSeasonNameAlert(eventSeasonNameModifyDto);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);

    }

    @Test
    void EVENT_SEASON_IMG_ALERT_TEST() {
        //given
        EventSeasonImgModifyDto eventSeasonImgModifyDto = EventSeasonImgModifyDto.builder()
                .eventSeasonId(eventSeason.getId())
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = eventSeasonService.eventSeasonImgAlert(eventSeasonImgModifyDto);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);

    }

    @Test
    void EVENT_SEASON_LIST_EVENT_SELECT_TEST() {
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

        EventSeason eventSeason = EventSeason.builder()
                .name("eventSeason")
                .build();
        eventSeasonRepository.save(eventSeason);

        Event event = Event.builder()
                .name("event")
                .worldCountryCityRegion(worldCountryCityRegion)
                .eventCategory(eventCategory)
                .eventSeason(eventSeason)
                .build();
        eventRepository.save(event);

        EventSeasonListDto eventSeasonListDto = EventSeasonListDto.builder()
                .eventSeasonId(eventSeason.getId())
                .worldCountryCityId(worldCountryCity.getId())
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        EventListVo eventListVo1 = new EventListVo(eventList);

        //when
        EventListVo eventListVo2 = eventSeasonService.eventSeasonEventListSelect(eventSeasonListDto);

        //then
        assertThat(eventListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventListVo2);

    }

    @Test
    void EVENT_SEASON_ALL_SELECT_TEST() {
        //given
        List<EventSeason> eventSeasonList = new ArrayList<>();
        eventSeasonList.add(eventSeason);

        EventSeasonListVo eventSeasonListVo1 = new EventSeasonListVo(eventSeasonList);

        //when
        EventSeasonListVo eventSeasonListVo2 = eventSeasonService.eventSeasonAllSelect();

        //then
        assertThat(eventSeasonListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventSeasonListVo2);
    }
}