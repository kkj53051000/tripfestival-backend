package com.tripfestival.service.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.dto.naturehotspot.NatureHotspotTypeDto;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotTypeAllListVo;
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
class NatureHotspotTypeServiceTest {

    @Autowired
    NatureHotspotTypeService natureHotspotTypeService;

    @Autowired
    NatureHotspotTypeRepository natureHotspotTypeRepository;

    @MockBean
    FileService fileService;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    NatureHotspotType natureHotspotType;

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("/test.jpg");

        natureHotspotType = NatureHotspotType.builder()
                .name("name")
                .img("img")
                .build();
        natureHotspotTypeRepository.save(natureHotspotType);
    }

    @Test
    void NATURE_HOTSPOT_TYPE_INSERT_TEST() {
        //given
        NatureHotspotTypeDto natureHotspotTypeDto = NatureHotspotTypeDto.builder()
                .name("name")
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = natureHotspotTypeService.natureHotspotTypeInsert(natureHotspotTypeDto);

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void NATURE_HOTSPOT_TYPE_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = natureHotspotTypeService.natureHotspotTypeDelete(natureHotspotType.getId());

        //then
        assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void NATURE_HOTSPOT_TYPE_ALL_SELECT_TEST() {
        //given
        List<NatureHotspotType> natureHotspotTypeList = new ArrayList<>();
        natureHotspotTypeList.add(natureHotspotType);

        NatureHotspotTypeAllListVo natureHotspotTypeAllListVo1 = new NatureHotspotTypeAllListVo(natureHotspotTypeList);

        //when
        NatureHotspotTypeAllListVo natureHotspotTypeAllListVo2 = natureHotspotTypeService.natureHotspotTypeAllSelect();

        //then
        assertThat(natureHotspotTypeAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(natureHotspotTypeAllListVo2);

    }
}