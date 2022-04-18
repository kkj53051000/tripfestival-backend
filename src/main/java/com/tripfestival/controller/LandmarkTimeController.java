package com.tripfestival.controller;

import com.tripfestival.request.LandmarkTimeProcessRequest;
import com.tripfestival.service.LandmarkTimeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkTimeController {
    private final LandmarkTimeService landmarkTimeService;

    @PostMapping("/landmarktimeprocess")
    public ResponseVo landmarkTimeProcess(@RequestBody LandmarkTimeProcessRequest req) {
        return landmarkTimeService.landmarkTimeInsert(req);
    }

    @PostMapping("/landmarktimeremove/{id}")
    public ResponseVo landmarkTimeRemove(@PathVariable("id") Long landmarkTimeId) {
        return landmarkTimeService.landmarkTimeDelete(landmarkTimeId);
    }
}
