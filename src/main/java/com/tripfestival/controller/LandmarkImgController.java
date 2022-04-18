package com.tripfestival.controller;

import com.tripfestival.dto.LandmarkImgProcessDto;
import com.tripfestival.service.LandmarkImgService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkImgController {
    private final LandmarkImgService landmarkImgService;

    @PostMapping("/landmarkimgprocess/{id}")
    public ResponseVo landmarkImgProcess(@PathVariable("id") Long landmarkId, @RequestPart List<MultipartFile> files) {
        LandmarkImgProcessDto landmarkImgProcessDto = new LandmarkImgProcessDto(files, landmarkId);

        System.out.println("aaaaaaaaaaaaa");

        return landmarkImgService.landmarkImgInsert(landmarkImgProcessDto);
    }

    @PostMapping("/landmarkimgremove/{id}")
    public ResponseVo landmarkImgRemove(@PathVariable("id") Long landmarkImgId) {
        return landmarkImgService.landmarkImgDelete(landmarkImgId);
    }
}