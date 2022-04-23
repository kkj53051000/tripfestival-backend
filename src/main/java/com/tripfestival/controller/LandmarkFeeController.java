package com.tripfestival.controller;

import com.tripfestival.dto.LandmarkFeeModifyDto;
import com.tripfestival.request.LandmarkFeeModifyRequest;
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

    @PostMapping("/landmarkfeemodify/{id}")
    public ResponseVo landmarkFeeModify(@PathVariable("id") Long landmarkFeeId, @RequestBody LandmarkFeeModifyRequest req) {
        LandmarkFeeModifyDto landmarkFeeModifyDto = LandmarkFeeModifyDto.builder()
                .landmarkFeeId(landmarkFeeId)
                .title(req.getTitle())
                .price(req.getPrice())
                .build();

        return landmarkFeeService.landmarkFeeAlert(landmarkFeeModifyDto);
    }
}
