package com.tripfestival.controller.landmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LandmarkControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void LANDMARK_PROCESS_TEST() throws Exception {
        //given
        WorldCountry worldCountry = new WorldCountry("t", "t", "t", "t", "t");
        worldCountryRepository.save(worldCountry);
        WorldCountryCity worldCountryCity = new WorldCountryCity("t", "t", worldCountry);
        worldCountryCityRepository.save(worldCountryCity);
        WorldCountryCityRegion worldCountryCityRegion = new WorldCountryCityRegion("t", "t", worldCountryCity);
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        Landmark landmark = Landmark.builder()
                .name("test")
                .description("test")
                .address("test")
                .homepage("test")
                .worldCountryCityRegion(worldCountryCityRegion)
                .build();

        landmarkRepository.save(landmark);

        LandmarkProcessRequest req = new LandmarkProcessRequest("t", "t", "t", "t", worldCountryCityRegion.getId());

        String value = objectMapper.writeValueAsString(req);


        //when
        //then
        this.mockMvc.perform(post("/api/landmarkProcess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(value))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }
}