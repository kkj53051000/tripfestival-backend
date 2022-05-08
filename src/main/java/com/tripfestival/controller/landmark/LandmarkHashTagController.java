package com.tripfestival.controller.landmark;

import com.tripfestival.request.landmark.LandmarkHashTagProcessRequest;
import com.tripfestival.service.landmark.LandmarkHashTagService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkHashTagAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkHashTagController {
    private final LandmarkHashTagService landmarkHashTagService;

    @PostMapping("/admin/landmarkHashTagProcess")
    public ResponseVo landmarkHashTagProcess(@RequestBody LandmarkHashTagProcessRequest req) {
        return landmarkHashTagService.landmarkHashTagInsert(req);
    }

    @PostMapping("/admin/landmarkHashTagRemove/{id}")
    public ResponseVo landmarkHashTagRemove(@PathVariable("id") Long landmarkHashTagId) {
        return landmarkHashTagService.landmarkHashTagDelete(landmarkHashTagId);
    }

    @GetMapping("/landmarkHashTagAllList")
    public LandmarkHashTagAllListVo landmarkHashTagAllList() {
        return landmarkHashTagService.landmarkHashTagAllListSelect();
    }
}
