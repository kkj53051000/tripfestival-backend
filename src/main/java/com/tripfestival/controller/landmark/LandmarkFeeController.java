package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkFeeModifyDto;
import com.tripfestival.request.landmark.LandmarkFeeModifyRequest;
import com.tripfestival.request.landmark.LandmarkFeeProcessRequest;
import com.tripfestival.service.landmark.LandmarkFeeService;
import com.tripfestival.vo.landmark.LandmarkFeeListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkFeeController {
    private LandmarkFeeService landmarkFeeService;

    @PostMapping("/landmarkFeeProcess")
    public ResponseVo landmarkFeeProcess(@RequestBody LandmarkFeeProcessRequest req) {
        return landmarkFeeService.landmarkFeeInsert(req);
    }

    @PostMapping("/landmarkFeeRemove/{id}")
    public ResponseVo landmarkFeeRemove(@PathVariable("id") Long landmarkFeeId) {
        return landmarkFeeService.landmarkFeeDelete(landmarkFeeId);
    }

    @PostMapping("/landmarkFeeModify/{id}")
    public ResponseVo landmarkFeeModify(@PathVariable("id") Long landmarkFeeId, @RequestBody LandmarkFeeModifyRequest req) {
        LandmarkFeeModifyDto landmarkFeeModifyDto = LandmarkFeeModifyDto.builder()
                .landmarkFeeId(landmarkFeeId)
                .title(req.getTitle())
                .price(req.getPrice())
                .build();

        return landmarkFeeService.landmarkFeeAlert(landmarkFeeModifyDto);
    }

    @GetMapping("/landmarkFeeList")
    public LandmarkFeeListVo landmarkFeeList(@RequestParam Long landmarkId) {
        return landmarkFeeService.landmarkFeeListSelect(landmarkId);
    }
}
