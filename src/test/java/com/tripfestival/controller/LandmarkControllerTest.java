package com.tripfestival.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.WorldCountry;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.repository.WorldCountryRepository;
import com.tripfestival.request.LandmarkProcessRequest;
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
    LandmarkRepository landmarkRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    public void LANDMARK_PROCESS_TEST() throws Exception {
        //given
        WorldCountry worldCountry = new WorldCountry("t", "t", "t", "t", "t");
        worldCountryRepository.save(worldCountry);
        WorldCountryCity worldCountryCity =new WorldCountryCity("t", "t", worldCountry);
        worldCountryCityRepository.save(worldCountryCity);

        Landmark landmark = Landmark.builder()
                .name("test")
                .description("test")
                .address("test")
                .homepage("test")
                .worldCountryCity(worldCountryCity)
                .build();

        landmarkRepository.save(landmark);

        LandmarkProcessRequest req = new LandmarkProcessRequest("t", "t", "t", "t", worldCountryCity.getId());

        String jsonReq = objectMapper.writeValueAsString(req);


        //when
        //then
        this.mockMvc.perform(post("/api/landmarkprocess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReq))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }
}