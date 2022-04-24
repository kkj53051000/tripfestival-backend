package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkImgProcessDto;
import com.tripfestival.request.landmark.LandmarkImgProcessRequest;
import com.tripfestival.service.landmark.LandmarkImgService;
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
    public ResponseVo landmarkImgProcess(
            @RequestPart List<MultipartFile> files,
            @RequestPart(name = "value") LandmarkImgProcessRequest req) {
        LandmarkImgProcessDto landmarkImgProcessDto = new LandmarkImgProcessDto(files, req.getLandmarkId());

        return landmarkImgService.landmarkImgInsert(landmarkImgProcessDto);
    }

    @PostMapping("/landmarkimgremove/{id}")
    public ResponseVo landmarkImgRemove(@PathVariable("id") Long landmarkImgId) {
        return landmarkImgService.landmarkImgDelete(landmarkImgId);
    }
}