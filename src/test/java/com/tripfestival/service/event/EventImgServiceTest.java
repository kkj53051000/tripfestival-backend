package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventImg;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventImgProcessDto;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventImgRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventImgListVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class EventImgServiceTest {

    @Autowired
    EventImgService eventImgService;

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
    EventImgRepository eventImgRepository;

    @MockBean
    FileService fileService;


    Event event;
    EventImg eventImg;

    ResponseVo successResponseVo = new ResponseVo(Response.SUCCESS, null);

    @BeforeEach
    void setup() {
        List<String> mockReturnStringList = new ArrayList<>();
        mockReturnStringList.add("test1.jpg");
        mockReturnStringList.add("test2.jpg");

        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFileList())).thenReturn(mockReturnStringList);

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

        eventImg = EventImg.builder()
                .event(event)
                .img("/test.jpg")
                .build();
        eventImgRepository.save(eventImg);
    }

    @Test
    void EVENT_IMG_INSERT_TEST() {
        //given
        EventImgProcessDto eventImgProcessDto = EventImgProcessDto.builder()
                .files(FileTestUtil.getMockMultipartFileList())
                .eventId(event.getId())
                .build();

        //when
        ResponseVo responseVo = eventImgService.eventImgInsert(eventImgProcessDto);


        //then
        Assertions.assertEquals(responseVo, successResponseVo);
    }

    @Test
    void EVENT_IMG_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventImgService.eventImgDelete(eventImg.getId());

        //then
        Assertions.assertEquals(responseVo, successResponseVo);
    }

    @Test
    void EVENT_IMG_LIST_TEST() {
        //given
        List<EventImg> eventImgList = new ArrayList<>();
        eventImgList.add(eventImg);

        EventImgListVo eventImgListVo1 = new EventImgListVo(eventImgList);

        //when
        EventImgListVo eventImgListVo2 = eventImgService.eventImgList(event.getId());

        //then
        assertThat(eventImgListVo1)
                .usingRecursiveComparison()
                .isEqualTo(eventImgListVo2);
    }
}