package com.tripfestival.controller.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.event.EventProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

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

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void EVENT_PROCESS_TEST() throws Exception {
        // given
        WorldCountry worldCountry = WorldCountry.builder().build();
        worldCountryRepository.save(worldCountry);

        WorldCountryCity worldCountryCity = WorldCountryCity.builder().build();
        worldCountryCityRepository.save(worldCountryCity);

        WorldCountryCityRegion worldCountryCityRegion = WorldCountryCityRegion.builder().build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        EventCategory eventCategory = EventCategory.builder().build();
        eventCategoryRepository.save(eventCategory);

        EventSeason eventSeason = EventSeason.builder().build();
        eventSeasonRepository.save(eventSeason);


        EventProcessRequest eventProcessRequest = EventProcessRequest.builder()
                .name("test")
                .description("test")
                .address("test")
                .visitor(1)
                .inout(1)
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .eventCategoryId(eventCategory.getId())
                .eventSeasonId(eventSeason.getId())
                .build();

        String value = objectMapper.writeValueAsString(eventProcessRequest);

        //when
        //then
        this.mockMvc.perform(multipart("/api/admin/eventProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())))
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }
}