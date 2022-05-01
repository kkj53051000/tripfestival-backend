package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class EventCategoryRepositoryTest {
    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Test
    public void EVENT_CATEGORY_SAVE_TEST() {
        // given
        EventCategory eventCategory = EventCategory.builder()
                .name("test")
                .img("test.png")
                .build();

        // when
        eventCategoryRepository.save(eventCategory);

        // then
        Assertions.assertEquals(eventCategory, eventCategoryRepository.findById(eventCategory.getId()).get());
    }
}