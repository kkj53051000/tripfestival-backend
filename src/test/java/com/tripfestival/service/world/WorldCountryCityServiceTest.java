package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.dto.world.WorldCountryCityNameModifyDto;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.request.world.WorldCountryCityProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryCityNameListVo;
import com.tripfestival.vo.world.WorldCountryCityNameVo;
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
class WorldCountryCityServiceTest {

    @Autowired
    WorldCountryCityService worldCountryCityService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @MockBean
    FileService fileService;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    WorldCountry worldCountry;
    WorldCountryCity worldCountryCity;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("/test.jpg");

        worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("worldCountryCity")
                .build();
        worldCountryCityRepository.save(worldCountryCity);
    }

    @Test
    void WORLD_COUNTRY_CITY_INSERT_TEST() {
        //given
        WorldCountryCityProcessRequest worldCountryCityProcessRequest = WorldCountryCityProcessRequest.builder()
                .name("name")
                .areaCode(123)
                .worldCountryId(worldCountry.getId())
                .build();

        //when
        ResponseVo responseVo = worldCountryCityService.worldCountryCityInsert(FileTestUtil.getMockMultipartFile(), worldCountryCityProcessRequest);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_CITY_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = worldCountryCityService.worldCountryCityDelete(worldCountryCity.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_CITY_NAME_ALERT_TEST() {
        //given
        WorldCountryCityNameModifyDto worldCountryCityNameModifyDto = WorldCountryCityNameModifyDto.builder()
                .worldCountryCityId(worldCountryCity.getId())
                .name("name")
                .build();

        //when
        ResponseVo responseVo = worldCountryCityService.worldCountryCityNameAlert(worldCountryCityNameModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_CITY_ALL_NAME_LIST_TEST() {
        //given
        List<WorldCountryCity> worldCountryCityList = new ArrayList<>();
        worldCountryCityList.add(worldCountryCity);

        WorldCountryCityNameListVo worldCountryCityNameListVo1 = new WorldCountryCityNameListVo(worldCountryCityList);

        //when
        WorldCountryCityNameListVo worldCountryCityNameListVo2 = worldCountryCityService.worldCountryCityAllNameList();

        //then
        assertThat(worldCountryCityNameListVo1)
                .usingRecursiveComparison()
                .isEqualTo(worldCountryCityNameListVo2);

    }

    @Test
    void WORLD_COUNTRY_CITY_NAME_SELECT_TEST() {
        //given
        WorldCountryCityNameVo worldCountryCityNameVo1 = WorldCountryCityNameVo.builder()
                .worldCountryCityName(worldCountryCity.getName())
                .build();

        //when
        WorldCountryCityNameVo worldCountryCityNameVo2 = worldCountryCityService.worldCountryCityNameSelect(worldCountryCity.getId());

        //then
        assertThat(worldCountryCityNameVo1)
                .usingRecursiveComparison()
                .isEqualTo(worldCountryCityNameVo2);


    }
}