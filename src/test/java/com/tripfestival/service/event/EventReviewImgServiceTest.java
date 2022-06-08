package com.tripfestival.service.event;

import com.tripfestival.domain.event.*;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventReviewImgProcessDto;
import com.tripfestival.repository.event.*;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
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

@SpringBootTest
@Transactional
class EventReviewImgServiceTest {

    @Autowired
    EventReviewImgService eventReviewImgService;

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
    EventReviewRepository eventReviewRepository;

    @Autowired
    EventReviewImgRepository eventReviewImgRepository;

    @MockBean
    FileService fileService;

    Event event;
    EventReview eventReview;
    EventReviewImg eventReviewImg;

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

        eventReview = EventReview.builder()
                .content("content")
                .score((byte) 10)
                .build();
        eventReviewRepository.save(eventReview);

        eventReviewImg = EventReviewImg.builder()
                .img("test.jpg")
                .eventReview(eventReview)
                .build();
        eventReviewImgRepository.save(eventReviewImg);
    }

    @Test
    void EVENT_REVIEW_IMG_INSERT_TEST() {
        //given
        EventReviewImgProcessDto eventReviewImgProcessDto = EventReviewImgProcessDto.builder()
                .files(FileTestUtil.getMockMultipartFileList())
                .eventReviewId(eventReview.getId())
                .build();

        //when
        ResponseVo responseVo = eventReviewImgService.eventReviewImgInsert(eventReviewImgProcessDto);

        //then
        Assertions.assertEquals(successResponseVo, responseVo);
    }

    @Test
    void EVENT_REVIEW_IMG_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = eventReviewImgService.eventReviewImgDelete(eventReviewImg.getId());

        //then
        Assertions.assertEquals(successResponseVo, responseVo);
    }
}