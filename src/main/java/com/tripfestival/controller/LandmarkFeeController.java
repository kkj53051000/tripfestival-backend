package com.tripfestival.controller;

import com.tripfestival.request.LandmarkFeeProcessRequest;
import com.tripfestival.service.LandmarkFeeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkFeeController {
    private LandmarkFeeService landmarkFeeService;

    @PostMapping("/landmarkfeeprocess")
    public ResponseVo landmarkFeeProcess(@RequestBody LandmarkFeeProcessRequest req) {
        return landmarkFeeService.landmarkFeeInsert(req);
    }

    @PostMapping("/landmarkfeeremove")
    public ResponseVo landmarkFeeRemove(@RequestParam Long landmarkFeeId) {
        return landmarkFeeService.landmarkFeeDelete(landmarkFeeId);
    }
}
