package com.tripfestival.controller.landmark;

import com.tripfestival.request.landmark.LandmarkHashTagProcessRequest;
import com.tripfestival.service.landmark.LandmarkHashTagService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkHashTagController {
    private final LandmarkHashTagService landmarkHashTagService;

    @PostMapping("/landmarkHashTagProcess")
    public ResponseVo landmarkHashTagProcess(LandmarkHashTagProcessRequest req) {
        return landmarkHashTagService.landmarkHashTagInsert(req);
    }

    @PostMapping("/landmarkHashTagRemove/{id}")
    public ResponseVo landmarkHashTagRemove(@PathVariable("id") Long landmarkHashTagId) {
        return landmarkHashTagService.landmarkHashTagDelete(landmarkHashTagId);
    }
}
