package com.tripfestival.controller;

import com.tripfestival.request.LandmarkReviewProcessRequest;
import com.tripfestival.service.LandmarkReviewService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkReviewController {
    private final LandmarkReviewService landmarkReviewService;

    @PostMapping("/landmarkreviewprocess")
    public ResponseVo landmarkReviewProcess(@RequestBody LandmarkReviewProcessRequest req) {
        return landmarkReviewService.landmarkReviewInsert(req);
    }

    @PostMapping("/landmarkreviewremove/{id}")
    public ResponseVo landmarkReviewRemove(@PathVariable("id") Long landmarkReviewId) {
        return landmarkReviewService.landmarkReviewDelete(landmarkReviewId);
    }
}
