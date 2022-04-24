package com.tripfestival.controller;

import com.tripfestival.dto.HotSightLandmarkDescriptionModifyDto;
import com.tripfestival.dto.HotSightLandmarkHotSightTwoModifyDto;
import com.tripfestival.request.HotSightLandmarkDescriptionModifyRequest;
import com.tripfestival.request.HotSightLandmarkHotSightTwoModifyRequest;
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

    @PostMapping("/hotsightlandmarkdescriptionmodify/{id}")
    public ResponseVo hotSightLandmarkDescriptionModify(
            @PathVariable("id") Long hotSightLandmarkId,
            @RequestBody HotSightLandmarkDescriptionModifyRequest req) {

        HotSightLandmarkDescriptionModifyDto hotSightLandmarkDescriptionModifyDto
                = new HotSightLandmarkDescriptionModifyDto(hotSightLandmarkId, req);

        return hotSightLandmarkService.hotSightDescriptionAlert(hotSightLandmarkDescriptionModifyDto);
    }

    @PostMapping("/hotsightlandmarkhotsighttwomodify/{id}")
    public ResponseVo hotSightLandmarkHotSightTwoModify(
            @PathVariable("id") Long hotSightLandmarkId,
            @RequestBody HotSightLandmarkHotSightTwoModifyRequest req) {

        HotSightLandmarkHotSightTwoModifyDto hotSightLandmarkHotSightTwoModifyDto =
                new HotSightLandmarkHotSightTwoModifyDto(hotSightLandmarkId, req);

        return hotSightLandmarkService.hotSightLandmarkHotSightTwoAlert(hotSightLandmarkHotSightTwoModifyDto);
    }
}
