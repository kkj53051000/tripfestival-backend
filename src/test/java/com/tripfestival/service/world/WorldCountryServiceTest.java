package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.dto.world.WorldCountryModifyDto;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryNameListVo;
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
class WorldCountryServiceTest {

    @Autowired
    WorldCountryService worldCountryService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @MockBean
    FileService fileService;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    WorldCountry worldCountry;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.jpg");

        worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);
    }

    @Test
    void WORLD_COUNTRY_INSERT_TEST() {
        //given
        WorldCountryProcessRequest worldCountryProcessRequest = WorldCountryProcessRequest.builder()
                .name("country")
                .exchangeRatio("1111")
                .currency("won")
                .capital("seoul")
                .build();

        //when
        ResponseVo responseVo = worldCountryService.worldCountryInsert(FileTestUtil.getMockMultipartFile(), worldCountryProcessRequest);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = worldCountryService.worldCountryDelete(worldCountry.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_ALERT_TEST() {
        //given
        WorldCountryModifyDto worldCountryModifyDto = WorldCountryModifyDto.builder()
                .worldCountryId(worldCountry.getId())
                .capital("capital")
                .currency("currency")
                .name("name")
                .exchangeRatio("exchangeRatio")
                .build();


        //when
        ResponseVo responseVo = worldCountryService.worldCountryAlert(worldCountryModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_NAME_LIST_SELECT_TEST() {
        //given
        List<WorldCountry> worldCountryList = new ArrayList<>();
        worldCountryList.add(worldCountry);

        WorldCountryNameListVo worldCountryNameListVo1 = new WorldCountryNameListVo(worldCountryList);

        //when
        WorldCountryNameListVo worldCountryNameListVo2 = worldCountryService.worldCountryNameListSelect();

        //then
        assertThat(worldCountryNameListVo1)
                .usingRecursiveComparison()
                .isEqualTo(worldCountryNameListVo2);

    }
}