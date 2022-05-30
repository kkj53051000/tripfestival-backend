package com.tripfestival.controller.naturehotspot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.naturehotspot.NatureHotspotNatureHotspotTypeModifyRequest;
import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotAllListVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotListVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NatureHotspotControllerTest extends BaseControllerTest {

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    NatureHotspotTypeRepository natureHotspotTypeRepository;

    @Autowired
    NatureHotspotRepository natureHotspotRepository;

    @Autowired
    LandmarkHashTagRepository landmarkHashTagRepository;



    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;
    Landmark landmark;
    NatureHotspotType natureHotspotType;
    NatureHotspot natureHotspot;

    @BeforeEach
    void setup() {
        WorldCountry worldCountry = WorldCountry.builder()
                .name("korea")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("seoul")
                .worldCountry(worldCountry)
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("gangnamgu")
                .worldCountryCity(worldCountryCity)
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);

        landmark = Landmark.builder()
                .name("landmark")
                .worldCountryCityRegion(worldCountryCityRegion)
                .build();
        landmarkRepository.save(landmark);

        natureHotspotType = NatureHotspotType.builder()
                .name("testType")
                .build();
        natureHotspotTypeRepository.save(natureHotspotType);

        natureHotspot = NatureHotspot.builder()
                .landmark(landmark)
                .natureHotspotType(natureHotspotType)
                .build();
        natureHotspotRepository.save(natureHotspot);
    }

    @Test
    void NATURE_HOTSPOT_PROCESS_TEST() throws Exception {
        //given
        NatureHotspotProcessRequest natureHotspotProcessRequest = NatureHotspotProcessRequest.builder()
                .natureHotspotTypeId(natureHotspotType.getId())
                .landmarkId(landmark.getId())
                .build();

        String value = objectMapper.writeValueAsString(natureHotspotProcessRequest);

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/natureHotspotProcess")
                .file(FileTestUtil.getMockMultipartFile())
                .file(new MockMultipartFile("value", "value", "application/json", value.getBytes())));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_REMOVE_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/natureHotspotRemove/" + natureHotspot.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_NATURE_HOTSPOT_TYPE_MODIFY_TEST() throws Exception {
        //given
        NatureHotspotNatureHotspotTypeModifyRequest natureHotspotNatureHotspotTypeModifyRequest = NatureHotspotNatureHotspotTypeModifyRequest.builder()
                .natureHotspotTypeId(natureHotspotType.getId())
                .build();

        String req = objectMapper.writeValueAsString(natureHotspotNatureHotspotTypeModifyRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/admin/natureHotspotNatureHotspotTypeModify/" + natureHotspot.getId())
                .contentType(jsonMediaType)
                .content(req));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_IMG_MODIFY_TEST() throws Exception {
        //given setup()

        //when
        ResultActions resultActions = mockMvc.perform(multipart("/api/admin/natureHotspotImgModify/" + natureHotspot.getId())
                .file(FileTestUtil.getMockMultipartFile()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_LIST_TEST() throws Exception {
        //given
        List<NatureHotspot> natureHotspotList = new ArrayList<>();
        natureHotspotList.add(natureHotspot);

        // Landmark HashTag
        LandmarkHashTag landmarkHashTag = LandmarkHashTag.builder()
                .name("test")
                .landmark(landmark)
                .build();
        landmarkHashTagRepository.save(landmarkHashTag);

        List<LandmarkHashTag> landmarkHashTagList = new ArrayList<>();
        landmarkHashTagList.add(landmarkHashTag);

        List<List<LandmarkHashTag>> landmarkHashTagListList = new ArrayList<>();
        landmarkHashTagListList.add(landmarkHashTagList);

        //response
        NatureHotspotListVo natureHotspotListVo = new NatureHotspotListVo(natureHotspotList, landmarkHashTagListList);

        String response = objectMapper.writeValueAsString(natureHotspotListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/natureHotspotList")
                .param("natureHotspotTypeId", String.valueOf(natureHotspotType.getId()))
                .param("worldCountryCityId", String.valueOf(worldCountryCity.getId()))
                .param("worldCountryCityRegionId", String.valueOf(worldCountryCityRegion.getId())));


        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void NATURE_HOTSPOT_ALL_LIST_TEST() throws Exception {
        //given
        List<NatureHotspot> natureHotspotList = new ArrayList<>();
        natureHotspotList.add(natureHotspot);

        NatureHotspotAllListVo natureHotspotAllListVo = new NatureHotspotAllListVo(natureHotspotList);

        String response = objectMapper.writeValueAsString(natureHotspotAllListVo);

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/natureHotspotAllList"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(MockMvcResultHandlers.print());
    }
}