package com.tripfestival.service.hotsight;

import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.domain.hotsight.HotSightTwo;
import com.tripfestival.dto.hotSight.HotSightTwoImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightTwoNameModifyDto;
import com.tripfestival.dto.hotSight.HotSightTwoProcessDto;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.repository.hotsight.HotSightTwoRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.util.FileTestUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotsight.HotSightTwoAllListVo;
import com.tripfestival.vo.hotsight.HotSightTwoListVo;
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
class HotSightTwoServiceTest {

    @Autowired
    HotSightTwoService hotSightTwoService;

    @Autowired
    HotSightTwoRepository hotSightTwoRepository;

    @Autowired
    HotSightOneRepository hotSightOneRepository;

    @MockBean
    FileService fileService;


    HotSightOne hotSightOne;
    HotSightTwo hotSightTwo;

    private final ResponseVo SUCCESS_RESPONSE_VO = new ResponseVo(Response.SUCCESS, null);

    @BeforeEach
    void setup() {
        Mockito.when(fileService.s3UploadProcess(FileTestUtil.getMockMultipartFile())).thenReturn("/test.jpg");

        hotSightOne = HotSightOne.builder()
                .name("hotSightOne")
                .img("test.jpg")
                .build();
        hotSightOneRepository.save(hotSightOne);

        hotSightTwo = HotSightTwo.builder()
                .name("htoSightTow")
                .img("test.jpg")
                .hotSightOne(hotSightOne)
                .build();
        hotSightTwoRepository.save(hotSightTwo);
    }

    @Test
    void HOT_SIGHT_TWO_INSERT_TEST() {
        //given
        HotSightTwoProcessDto hotSightTwoProcessDto = HotSightTwoProcessDto.builder()
                .name("changeName")
                .file(FileTestUtil.getMockMultipartFile())
                .hotSightOneId(hotSightOne.getId())
                .build();

        //when
        ResponseVo responseVo = hotSightTwoService.hotSightTwoInsert(hotSightTwoProcessDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_TWO_DELETE_TEST() {
        //given setup()


        //when
        ResponseVo responseVo = hotSightTwoService.hotSightTwoDelete(hotSightTwo.getId());

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_TWO_NAME_ALERT_TEST() {
        //given
        HotSightTwoNameModifyDto hotSightTwoNameModifyDto = HotSightTwoNameModifyDto.builder()
                .name("changeHotSightTwo")
                .hotSightTwoId(hotSightTwo.getId())
                .build();

        //when
        ResponseVo responseVo = hotSightTwoService.hostSightTwoNameAlert(hotSightTwoNameModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_TWO_IMG_ALERT_TEST() {
        //given
        HotSightTwoImgModifyDto hotSightTwoImgModifyDto = HotSightTwoImgModifyDto.builder()
                .hotSightTwoId(hotSightTwo.getId())
                .file(FileTestUtil.getMockMultipartFile())
                .build();

        //when
        ResponseVo responseVo = hotSightTwoService.hotSightTwoImgAlert(hotSightTwoImgModifyDto);

        //then
        Assertions.assertEquals(SUCCESS_RESPONSE_VO, responseVo);

    }

    @Test
    void HOT_SIGHT_TWO_LIST_SELECT_TEST() {
        //given
        List<HotSightTwo> hotSightTwoList = new ArrayList<>();
        hotSightTwoList.add(hotSightTwo);

        HotSightTwoListVo hotSightTwoListVo1 = new HotSightTwoListVo(hotSightTwoList);

        //when
        HotSightTwoListVo hotSightTwoListVo2 = hotSightTwoService.hotSightTwoListSelect(hotSightOne.getId());

        //then
        assertThat(hotSightTwoListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotSightTwoListVo2);

    }

    @Test
    void HOT_SIGHT_TOW_ALL_LIST_SELECT_TEST() {
        //given
        List<HotSightTwo> hotSightTwoList = new ArrayList<>();
        hotSightTwoList.add(hotSightTwo);

        HotSightTwoAllListVo hotSightTwoAllListVo1 = new HotSightTwoAllListVo(hotSightTwoList);

        //when
        HotSightTwoAllListVo hotSightTwoAllListVo2 = hotSightTwoService.hotSightTwoAllListSelect();

        //then
        assertThat(hotSightTwoAllListVo1)
                .usingRecursiveComparison()
                .isEqualTo(hotSightTwoAllListVo2);


    }
}