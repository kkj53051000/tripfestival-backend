package com.tripfestival.controller.landmark;

import com.tripfestival.request.landmark.LandmarkReviewProcessRequest;
import com.tripfestival.service.landmark.LandmarkReviewService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkReviewListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkReviewController {
    private final LandmarkReviewService landmarkReviewService;

    @PostMapping("/landmarkReviewProcess")
    public ResponseVo landmarkReviewProcess(@RequestBody LandmarkReviewProcessRequest req) {
        return landmarkReviewService.landmarkReviewInsert(req);
    }

    @PostMapping("/landmarkReviewRemove/{id}")
    public ResponseVo landmarkReviewRemove(@PathVariable("id") Long landmarkReviewId) {
        return landmarkReviewService.landmarkReviewDelete(landmarkReviewId);
    }

    @GetMapping("/landmarkReviewList")
    public LandmarkReviewListVo landmarkReviewList(@RequestParam Long landmarkId) {
        return landmarkReviewService.landmarkReviewListSelect(landmarkId);
    }
}
