package com.tripfestival.service.hotspot;

import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.dto.hotspot.HotspotTypeNameModifyDto;
import com.tripfestival.dto.hotspot.HotspotTypeProcessDto;
import com.tripfestival.repository.hotspot.HotspotTypeRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotTypeAllListVo;
import com.tripfestival.vo.hotspot.HotspotTypeNameVo;
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
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class HotspotTypeServiceTest {

    @Autowired
    HotspotTypeService hotspotTypeService;

    @Autowired
    HotspotTypeRepository hotspotTypeRepository;

    @MockBean
    FileService fileService;


    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    HotspotType hotspotType;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("test.jpg");

        hotspotType = HotspotType.builder()
                .name("hotspotType")
                .img("test.jpg")
                .build();
        hotspotTypeRepository.save(hotspotType);
    }

    @Test
    void HOTSPOT_TYPE_INSERT_TEST() {
        //given
        HotspotTypeProcessDto hotspotTypeProcessDto = HotspotTypeProcessDto.builder()
                .name("hotspotType")
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = hotspotTypeService.hotspotTypeInsert(hotspotTypeProcessDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOTSPOT_TYPE_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = hotspotTypeService.hotspotTypeDelete(hotspotType.getId());

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOTSPOT_TYPE_NAME_ALERT_TEST() {
        //given
        HotspotTypeNameModifyDto hotspotTypeNameModifyDto = HotspotTypeNameModifyDto.builder()
                .hotspotTypeId(hotspotType.getId())
                .name("changeHotspotType")
                .build();

        //when
        ResponseVo responseVo = hotspotTypeService.hotspotTypeNameAlert(hotspotTypeNameModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOTSPOT_TYPE_ALL_LIST_SELECT_TEST() {
        //given
        List<HotspotType> hotspotTypeList = new ArrayList<>();
        hotspotTypeList.add(hotspotType);

        HotspotTypeAllListVo hotspotTypeAllListVo1 = new HotspotTypeAllListVo(hotspotTypeList);

        //when
        HotspotTypeAllListVo hotspotTypeAllListVo2 = hotspotTypeService.hotspotTypeAllListSelect();

        //then
        assertThat(hotspotTypeAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotspotTypeAllListVo2);

    }

    @Test
    void HOTSPOT_TYPE_NAME_SELECT_TEST() {
        //given
        HotspotTypeNameVo hotspotTypeNameVo1 = HotspotTypeNameVo.builder()
                .hotSpotTypeName(hotspotType.getName())
                .build();

        //when
        HotspotTypeNameVo hotspotTypeNameVo2 = hotspotTypeService.HotspotTypeNameSelect(hotspotType.getId());

        //then
        assertThat(hotspotTypeNameVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotspotTypeNameVo2);

    }
}