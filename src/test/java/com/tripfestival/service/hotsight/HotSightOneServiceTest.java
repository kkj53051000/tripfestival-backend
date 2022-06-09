package com.tripfestival.service.hotsight;

import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.dto.hotSight.HotSightOneImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightOneNameModifyDto;
import com.tripfestival.dto.hotSight.HotSightOneProcessDto;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotsight.HotSightOneListVo;
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
class HotSightOneServiceTest {

    @Autowired
    HotSightOneService hotSightOneService;

    @Autowired
    HotSightOneRepository hotSightOneRepository;

    @MockBean
    FileService fileService;

    HotSightOne hotSightOne;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("/test.jpg");

        hotSightOne = HotSightOne.builder()
                .name("hotSightOne")
                .img("test.jpg")
                .build();
        hotSightOneRepository.save(hotSightOne);
    }

    @Test
    void HOT_SIGHT_ONE_INSERT_TEST() {
        //given
        HotSightOneProcessDto hotSightOneProcessDto = HotSightOneProcessDto.builder()
                .name("name")
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = hotSightOneService.hotSightOneInsert(hotSightOneProcessDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_ONE_DELETE_TEST() {
        //given setup()

        //when
        ResponseVo responseVo = hotSightOneService.hotSightOneDelete(hotSightOne.getId());

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_ONE_NAME_ALERT_TEST() {
        //given
        HotSightOneNameModifyDto hotSightOneNameModifyDto = HotSightOneNameModifyDto.builder()
                .hotSightOneId(hotSightOne.getId())
                .name("changeName")
                .build();

        //when
        ResponseVo responseVo = hotSightOneService.hotSightOneNameAlert(hotSightOneNameModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);
    }

    @Test
    void HOT_SIGHT_ONE_IMG_ALERT_TEST() {
        //given
        HotSightOneImgModifyDto hotSightOneImgModifyDto = HotSightOneImgModifyDto.builder()
                .hotSightOneId(hotSightOne.getId())
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = hotSightOneService.hotSightOneImgAlert(hotSightOneImgModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_ONE_ALL_LIST_SELECT_TEST() {
        //given
        List<HotSightOne> hotSightOneList = new ArrayList<>();
        hotSightOneList.add(hotSightOne);

        HotSightOneListVo hotSightOneListVo1 = new HotSightOneListVo(hotSightOneList);

        //when
        HotSightOneListVo hotSightOneListVo2 = hotSightOneService.hotSightOneAllListSelect();

        //then
        assertThat(hotSightOneListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotSightOneListVo2);
    }
}