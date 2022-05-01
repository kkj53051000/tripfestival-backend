package com.tripfestival.service.event;

import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.dto.event.EventCategoryProcessDto;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.util.FileTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventCategoryServiceTest {

    @Autowired
    EventCategoryService eventCategoryService;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Test
    void EVENT_CATEGORY_INSERT_TEST() {
        //given
        EventCategoryProcessDto req = new EventCategoryProcessDto(FileTestUtil.getMockMultipartFile(), "test");

        //when
        eventCategoryService.eventCategoryInsert(req);

        //then
        Assertions.assertNotNull(eventCategoryRepository.findByName("test").get());
    }
}