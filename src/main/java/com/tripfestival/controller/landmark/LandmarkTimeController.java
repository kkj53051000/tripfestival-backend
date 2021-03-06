package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkTimeModifyDto;
import com.tripfestival.request.landmark.LandmarkTimeModifyRequest;
import com.tripfestival.request.landmark.LandmarkTimeProcessRequest;
import com.tripfestival.service.landmark.LandmarkTimeService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkTimeAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkTimeController {
    private final LandmarkTimeService landmarkTimeService;

    @PostMapping("/admin/landmarkTimeProcess")
    public ResponseVo landmarkTimeProcess(@RequestBody LandmarkTimeProcessRequest req) {
        return landmarkTimeService.landmarkTimeInsert(req);
    }

    @PostMapping("/admin/landmarkTimeRemove/{id}")
    public ResponseVo landmarkTimeRemove(@PathVariable("id") Long landmarkTimeId) {
        return landmarkTimeService.landmarkTimeDelete(landmarkTimeId);
    }

    @PostMapping("/admin/landmarkTimeModify/{id}")
    public ResponseVo landmarkTimeModify(
            @PathVariable("id") Long landmarkTimeId,
            @RequestBody LandmarkTimeModifyRequest req) {
        LandmarkTimeModifyDto landmarkTimeModifyDto = LandmarkTimeModifyDto.builder()
                .landmarkTimeId(landmarkTimeId)
                .title(req.getTitle())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .build();

        return landmarkTimeService.landmarkTimeAlert(landmarkTimeModifyDto);
    }

    @GetMapping("/landmarkTimeAllList")
    public LandmarkTimeAllListVo landmarkTimeAllList() {
        return landmarkTimeService.landmarkTimeAllListSelect();
    }
}
