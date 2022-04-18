package com.tripfestival.controller;

import com.tripfestival.request.LandmarkProcessRequest;
import com.tripfestival.service.LandmarkService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkController {
    private final LandmarkService landmarkService;

    @PostMapping("/landmarkprocess")
    public ResponseVo landmarkProcess(@RequestBody LandmarkProcessRequest req) {
        return landmarkService.landmarkInsert(req);
    }

    @PostMapping("/landmarkremove/{id}")
    public ResponseVo landmarkRemove(@PathVariable("id") Long landmarkId) {
        return landmarkService.landmarkDelete(landmarkId);
    }

}
