package com.tripfestival.controller;

import com.tripfestival.request.LandmarkFeeProcessRequest;
import com.tripfestival.service.LandmarkFeeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkFeeController {
    private LandmarkFeeService landmarkFeeService;

    @PostMapping("/landmarkfeeprocess")
    public ResponseVo landmarkFeeProcess(@RequestBody LandmarkFeeProcessRequest req) {
        return landmarkFeeService.landmarkFeeInsert(req);
    }

    @PostMapping("/landmarkfeeremove/{id}")
    public ResponseVo landmarkFeeRemove(@PathVariable("id") Long landmarkFeeId) {
        return landmarkFeeService.landmarkFeeDelete(landmarkFeeId);
    }
}
