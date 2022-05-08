package com.tripfestival.controller.hotsight;

import com.tripfestival.dto.hotSight.HotSightLandmarkImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightLandmarkHotSightTwoModifyDto;
import com.tripfestival.request.hotsight.HotSightLandmarkHotSightTwoModifyRequest;
import com.tripfestival.request.hotsight.HotSightLandmarkProcessRequest;
import com.tripfestival.service.hotsight.HotSightLandmarkService;
import com.tripfestival.vo.hotsight.HotSightLandmarkAllListVo;
import com.tripfestival.vo.hotsight.HotSightLandmarkListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightLandmarkController {  // 특별한 관광지

    private final HotSightLandmarkService hotSightLandmarkService;

    @PostMapping("/admin/hotSightLandmarkProcess")
    public ResponseVo hotSightLandmarkProcess(
            @RequestBody HotSightLandmarkProcessRequest req) {


        return hotSightLandmarkService.hotSightLandmarkInsert(req);
    }

    @PostMapping("/admin/hotSightLandmarkRemove/{id}")
    public ResponseVo hotSightLandmarkRemove(@PathVariable("id") Long hotSightLandmarkId) {
        return hotSightLandmarkService.hotSightLandmarkDelete(hotSightLandmarkId);
    }

    @PostMapping("/admin/hotSightLandmarkHotSightTwoModify/{id}")
    public ResponseVo hotSightLandmarkHotSightTwoModify(
            @PathVariable("id") Long hotSightLandmarkId,
            @RequestBody HotSightLandmarkHotSightTwoModifyRequest req) {

        HotSightLandmarkHotSightTwoModifyDto hotSightLandmarkHotSightTwoModifyDto =
                new HotSightLandmarkHotSightTwoModifyDto(hotSightLandmarkId, req);

        return hotSightLandmarkService.hotSightLandmarkHotSightTwoAlert(hotSightLandmarkHotSightTwoModifyDto);
    }

    @GetMapping("/hotSightLandmarkList")
    public HotSightLandmarkListVo hotSightLandmarkList(@RequestParam Long hotSightTwoId) {
        return hotSightLandmarkService.hotSightLandmarkListSelect(hotSightTwoId);
    }

    @GetMapping("/hotSightLandmarkAllList")
    public HotSightLandmarkAllListVo hotSightLandmarkAllList() {
        return hotSightLandmarkService.hotSightLandmarkAllListSelect();
    }
}
