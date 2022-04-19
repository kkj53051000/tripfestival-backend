package com.tripfestival.controller;

import com.tripfestival.dto.LandmarkReviewImgProcessDto;
import com.tripfestival.request.LandmarkReviewImgProcessRequest;
import com.tripfestival.service.LandmarkReviewImgService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkReviewImgController {
    private final LandmarkReviewImgService landmarkReviewImgService;

    @PostMapping("/landmarkreviewimgprocess")
    public ResponseVo landmarkReviewImgProcess(
            @RequestPart List<MultipartFile> files,
            @RequestPart(name = "value")LandmarkReviewImgProcessRequest req) {

        LandmarkReviewImgProcessDto landmarkReviewImgProcessDto = new LandmarkReviewImgProcessDto(files, req.getLandmarkReviewId());

        return landmarkReviewImgService.landmarkReviewImgInsert(landmarkReviewImgProcessDto);
    }

    @PostMapping("/landmarkreviewimgremove/{id}")
    public ResponseVo landmarkReviewImgRemove(@PathVariable("id") Long landmarkReviewImgId) {
        return landmarkReviewImgService.landmarkReviewImgDelete(landmarkReviewImgId);
    }
}
