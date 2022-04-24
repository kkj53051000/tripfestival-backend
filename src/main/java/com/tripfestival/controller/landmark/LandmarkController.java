package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkModifyDto;
import com.tripfestival.request.landmark.LandmarkModifyRequest;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.service.landmark.LandmarkService;
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
        System.out.println(req.getName());
        return landmarkService.landmarkInsert(req);
    }

    @PostMapping("/landmarkremove/{id}")
    public ResponseVo landmarkRemove(@PathVariable("id") Long landmarkId) {
        return landmarkService.landmarkDelete(landmarkId);
    }

    @PostMapping("/landmarkmodify/{id}")
    public ResponseVo landmarkModify(@PathVariable("id") Long landmarkId,
                                     @RequestBody LandmarkModifyRequest req) {

        LandmarkModifyDto landmarkModifyDto = LandmarkModifyDto.builder()
                .landmarkId(landmarkId)
                .name(req.getName())
                .description(req.getDescription())
                .address(req.getAddress())
                .homepage(req.getHomepage())
                .worldCountryCityId(req.getWorldCountryCityId())
                .build();

        return landmarkService.landmarkAlert(landmarkModifyDto);
    }


}
