package com.tripfestival.controller;

import com.tripfestival.dto.LandmarkImgProcessDto;
import com.tripfestival.service.LandmarkImgService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LandmarkImgController {
    private final LandmarkImgService landmarkImgService;

    @PostMapping("/landmarkimgprocess")
    public ResponseVo landmarkImgProcess(@RequestParam List<MultipartFile> files, Long landmarkId) {
        LandmarkImgProcessDto landmarkImgProcessDto = new LandmarkImgProcessDto(files, landmarkId);

        return landmarkImgService.landmarkImgInsert(landmarkImgProcessDto);
    }

    @PostMapping("/landmarkimgremove")
    public ResponseVo landmarkImgRemove(Long landmarkImgId) {
        return landmarkImgService.landmarkImgDelete(landmarkImgId);
    }
}