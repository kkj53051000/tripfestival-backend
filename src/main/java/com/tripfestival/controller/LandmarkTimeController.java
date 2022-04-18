package com.tripfestival.controller;

import com.tripfestival.request.LandmarkTimeProcessRequest;
import com.tripfestival.service.LandmarkTimeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkTimeController {
    private final LandmarkTimeService landmarkTimeService;

    @PostMapping("/landmarktimeprocess")
    public ResponseVo landmarkTimeProcess(LandmarkTimeProcessRequest req) {
        return landmarkTimeService.landmarkTimeInsert(req);
    }

    @PostMapping("/landmarktimeremove")
    public ResponseVo landmarkTimeRemove(Long landmarkTimeId) {
        return landmarkTimeService.landmarkTimeDelete(landmarkTimeId);
    }
}
