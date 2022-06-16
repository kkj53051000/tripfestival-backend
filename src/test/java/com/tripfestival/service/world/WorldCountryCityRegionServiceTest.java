package com.tripfestival.service.world;

import com.tripfestival.domain.world.WorldCountry;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.world.WorldCountryCityRegionNameModifyDto;
import com.tripfestival.dto.world.WorldCountryCityRegionProcessDto;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.repository.world.WorldCountryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.WorldCountryCityRegionListVo;
import com.tripfestival.vo.world.WorldCountryCityRegionNameVo;
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
class WorldCountryCityRegionServiceTest {

    @Autowired
    WorldCountryCityRegionService worldCountryCityRegionService;

    @Autowired
    WorldCountryRepository worldCountryRepository;

    @Autowired
    WorldCountryCityRepository worldCountryCityRepository;

    @Autowired
    WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @MockBean
    FileService fileService;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    WorldCountryCity worldCountryCity;
    WorldCountryCityRegion worldCountryCityRegion;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile()))
                .thenReturn("test.jpg");

        WorldCountry worldCountry = WorldCountry.builder()
                .name("worldCountry")
                .build();
        worldCountryRepository.save(worldCountry);

        worldCountryCity = WorldCountryCity.builder()
                .name("worldCountryCity")
                .build();
        worldCountryCityRepository.save(worldCountryCity);

        worldCountryCityRegion = WorldCountryCityRegion.builder()
                .name("worldCountryCityRegion")
                .worldCountryCity(worldCountryCity)
                .build();
        worldCountryCityRegionRepository.save(worldCountryCityRegion);
    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_INSERT_TEST() {
        //given
        WorldCountryCityRegionProcessDto worldCountryCityRegionProcessDto = WorldCountryCityRegionProcessDto.builder()
                .file(FileTestUtil.getMockMultipartFile())
                .name("name")
                .worldCountryCityId(worldCountryCity.getId())
                .build();

        //when
        ResponseVo responseVo = worldCountryCityRegionService.worldCountryCityRegionInsert(worldCountryCityRegionProcessDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = worldCountryCityRegionService.worldCountryCityRegionDelete(worldCountryCityRegion.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_NAME_ALERT_TEST() {
        //given
        WorldCountryCityRegionNameModifyDto worldCountryCityRegionNameModifyDto = WorldCountryCityRegionNameModifyDto.builder()
                .name("name")
                .worldCountryCityRegionId(worldCountryCityRegion.getId())
                .build();

        //when
        ResponseVo responseVo = worldCountryCityRegionService.worldCountryCityRegionNameAlert(worldCountryCityRegionNameModifyDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_LIST_SELECT_TEST() {
        //given
        List<WorldCountryCityRegion> worldCountryCityRegionList = new ArrayList<>();
        worldCountryCityRegionList.add(worldCountryCityRegion);

        WorldCountryCityRegionListVo worldCountryCityRegionListVo1 = new WorldCountryCityRegionListVo(worldCountryCityRegionList);

        //when
        WorldCountryCityRegionListVo worldCountryCityRegionListVo2 = worldCountryCityRegionService.worldCountryCityRegionListSelect(worldCountryCity.getId());

        //then
        assertThat(worldCountryCityRegionListVo1)
                .usingRecursiveComparison()
                .isEqualTo(worldCountryCityRegionListVo2);

    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_ALL_LIST_SELECT_TEST() {
        //given
        List<WorldCountryCityRegion> worldCountryCityRegionList = new ArrayList<>();
        worldCountryCityRegionList.add(worldCountryCityRegion);

        WorldCountryCityRegionListVo worldCountryCityRegionListVo1 = new WorldCountryCityRegionListVo(worldCountryCityRegionList);

        //when
        WorldCountryCityRegionListVo worldCountryCityRegionListVo2 = worldCountryCityRegionService.worldCountryCityRegionAllListSelect();

        //then
        assertThat(worldCountryCityRegionListVo1)
                .usingRecursiveComparison()
                .isEqualTo(worldCountryCityRegionListVo2);

    }

    @Test
    void WORLD_COUNTRY_CITY_REGION_NAME_SELECT_TEST() {
        //given
        WorldCountryCityRegionNameVo worldCountryCityRegionNameVo1 = WorldCountryCityRegionNameVo.builder()
                .worldCountryCityRegionName(worldCountryCityRegion.getName())
                .build();

        //when
        WorldCountryCityRegionNameVo worldCountryCityRegionNameVo2 = worldCountryCityRegionService.worldCountryCityRegionNameSelect(worldCountryCityRegion.getId());

        //then
        assertThat(worldCountryCityRegionNameVo1)
                .usingRecursiveComparison()
                .isEqualTo(worldCountryCityRegionNameVo2);

    }
}