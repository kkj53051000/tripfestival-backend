package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkReviewImgProcessDto;
import com.tripfestival.request.landmark.LandmarkReviewImgProcessRequest;
import com.tripfestival.service.landmark.LandmarkReviewImgService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkReviewImgListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkReviewImgController {
    private final LandmarkReviewImgService landmarkReviewImgService;

    @PostMapping("/landmarkReviewImgProcess")
    public ResponseVo landmarkReviewImgProcess(
            @RequestPart List<MultipartFile> files,
            @RequestPart(name = "value")LandmarkReviewImgProcessRequest req) {

        LandmarkReviewImgProcessDto landmarkReviewImgProcessDto = new LandmarkReviewImgProcessDto(files, req.getLandmarkReviewId());

        return landmarkReviewImgService.landmarkReviewImgInsert(landmarkReviewImgProcessDto);
    }

    @PostMapping("/landmarkReviewImgRemove/{id}")
    public ResponseVo landmarkReviewImgRemove(@PathVariable("id") Long landmarkReviewImgId) {
        return landmarkReviewImgService.landmarkReviewImgDelete(landmarkReviewImgId);
    }

    @GetMapping("/landmarkReviewImgList")
    public LandmarkReviewImgListVo landmarkReviewImgList(@RequestParam Long landmarkReviewId) {
        return landmarkReviewImgService.landmarkReviewImgListSelect(landmarkReviewId);
    }
}
