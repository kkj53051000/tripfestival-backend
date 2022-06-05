package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkListDto;
import com.tripfestival.dto.landmark.LandmarkModifyDto;
import com.tripfestival.dto.landmark.LandmarkProcessDto;
import com.tripfestival.request.hotsight.HotSightOneProcessRequest;
import com.tripfestival.request.hotsight.HotSightTwoProcessRequest;
import com.tripfestival.request.landmark.LandmarkModifyRequest;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.service.landmark.LandmarkService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkAllCountVo;
import com.tripfestival.vo.landmark.LandmarkAllListVo;
import com.tripfestival.vo.landmark.LandmarkListVo;
import com.tripfestival.vo.landmark.LandmarkVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LandmarkController {  // 관광지
    private final LandmarkService landmarkService;

    @PostMapping("/admin/landmarkProcess")
    public ResponseVo landmarkProcess(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "value") LandmarkProcessRequest req) {

        LandmarkProcessDto landmarkProcessDto = new LandmarkProcessDto(file, req);

        return landmarkService.landmarkInsert(landmarkProcessDto);
    }

    @PostMapping("/admin/landmarkRemove/{id}")
    public ResponseVo landmarkRemove(@PathVariable("id") Long landmarkId) {
        return landmarkService.landmarkDelete(landmarkId);
    }

    @PostMapping("/admin/landmarkClear/{id}")
    public ResponseVo landmarkClear(@PathVariable("id") Long landmarkId) {
        return landmarkService.landmarkClearDelete(landmarkId);
    }

    @PostMapping("/admin/landmarkModify/{id}")
    public ResponseVo landmarkModify(@PathVariable("id") Long landmarkId,
                                     @RequestBody LandmarkModifyRequest req) {

        LandmarkModifyDto landmarkModifyDto = new LandmarkModifyDto(landmarkId, req);

        return landmarkService.landmarkAlert(landmarkModifyDto);
    }

    @GetMapping("/landmarkList")
    public LandmarkListVo landmarkList(
            @RequestParam Long worldCountryCityRegionId,
            @RequestParam Long worldCountryCityId) {

        LandmarkListDto landmarkListDto = LandmarkListDto.builder()
                .worldCountryCityRegionId(worldCountryCityRegionId)
                .worldCountryCityId(worldCountryCityId)
                .build();

        return landmarkService.landmarkListSelect(landmarkListDto);
    }

    @GetMapping("/landmark")
    public LandmarkVo landmark(@RequestParam Long landmarkId) {
        return landmarkService.landmarkSelect(landmarkId);
    }

    @GetMapping("/landmarkAllList")
    public LandmarkAllListVo landmarkAllList() {
        return landmarkService.landmarkAllListSelect();
    }

    @GetMapping("/landmarkAllCount")
    public LandmarkAllCountVo landmarkAllCount() {
        return landmarkService.landmarkAllCountSelect();
    }
}
