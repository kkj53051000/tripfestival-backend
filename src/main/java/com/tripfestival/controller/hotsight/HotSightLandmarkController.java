package com.tripfestival.controller.hotsight;

import com.tripfestival.dto.hotSight.HotSightLandmarkDescriptionModifyDto;
import com.tripfestival.dto.hotSight.HotSightLandmarkHotSightTwoModifyDto;
import com.tripfestival.request.hotsight.HotSightLandmarkDescriptionModifyRequest;
import com.tripfestival.request.hotsight.HotSightLandmarkHotSightTwoModifyRequest;
import com.tripfestival.request.hotsight.HotSightLandmarkProcessRequest;
import com.tripfestival.service.hotsight.HotSightLandmarkService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightLandmarkController {  // 특별한 관광지

    private final HotSightLandmarkService hotSightLandmarkService;

    @PostMapping("/hotSightLandmarkProcess")
    public ResponseVo hotSightLandmarkProcess(@RequestBody HotSightLandmarkProcessRequest req) {
        return hotSightLandmarkService.hotSightLandmarkInsert(req);
    }

    @PostMapping("/hotsightLandmarkRemove/{id}")
    public ResponseVo hotSightLandmarkRemove(@PathVariable("id") Long hotSightLandmarkId) {
        return hotSightLandmarkService.hotSightLandmarkDelete(hotSightLandmarkId);
    }

    @PostMapping("/hotsightLandmarkDescriptionModify/{id}")
    public ResponseVo hotSightLandmarkDescriptionModify(
            @PathVariable("id") Long hotSightLandmarkId,
            @RequestBody HotSightLandmarkDescriptionModifyRequest req) {

        HotSightLandmarkDescriptionModifyDto hotSightLandmarkDescriptionModifyDto
                = new HotSightLandmarkDescriptionModifyDto(hotSightLandmarkId, req);

        return hotSightLandmarkService.hotSightDescriptionAlert(hotSightLandmarkDescriptionModifyDto);
    }

    @PostMapping("/hotsightLandmarkHotSightTwoModify/{id}")
    public ResponseVo hotSightLandmarkHotSightTwoModify(
            @PathVariable("id") Long hotSightLandmarkId,
            @RequestBody HotSightLandmarkHotSightTwoModifyRequest req) {

        HotSightLandmarkHotSightTwoModifyDto hotSightLandmarkHotSightTwoModifyDto =
                new HotSightLandmarkHotSightTwoModifyDto(hotSightLandmarkId, req);

        return hotSightLandmarkService.hotSightLandmarkHotSightTwoAlert(hotSightLandmarkHotSightTwoModifyDto);
    }
}