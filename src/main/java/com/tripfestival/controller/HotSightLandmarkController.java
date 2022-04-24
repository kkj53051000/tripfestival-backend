package com.tripfestival.controller;

import com.tripfestival.request.HotSightLandmarkProcessRequest;
import com.tripfestival.service.HotSightLandmarkService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightLandmarkController {
    private final HotSightLandmarkService hotSightLandmarkService;

    @PostMapping("/hotsightlandmarkprocess")
    public ResponseVo hotSightLandmarkProcess(@RequestBody HotSightLandmarkProcessRequest req) {
        return hotSightLandmarkService.hotSightLandmarkInsert(req);
    }

    @PostMapping("/hotsightlandmarkremove/{id}")
    public ResponseVo hotSightLandmarkRemove(@PathVariable("id") Long hotSightLandmarkId) {
        return hotSightLandmarkService.hotSightLandmarkDelete(hotSightLandmarkId);
    }
}
