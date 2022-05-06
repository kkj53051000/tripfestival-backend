package com.tripfestival.controller.landmark;

import com.tripfestival.dto.landmark.LandmarkModifyDto;
import com.tripfestival.dto.landmark.LandmarkProcessDto;
import com.tripfestival.request.landmark.LandmarkModifyRequest;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.service.landmark.LandmarkService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkAllListVo;
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
            @RequestBody LandmarkProcessRequest req) {

        LandmarkProcessDto landmarkProcessDto = new LandmarkProcessDto(file, req);

        return landmarkService.landmarkInsert(landmarkProcessDto);
    }

    @PostMapping("/admin/landmarkRemove/{id}")
    public ResponseVo landmarkRemove(@PathVariable("id") Long landmarkId) {
        return landmarkService.landmarkDelete(landmarkId);
    }

    @PostMapping("/admin/landmarkModify/{id}")
    public ResponseVo landmarkModify(@PathVariable("id") Long landmarkId,
                                     @RequestBody LandmarkModifyRequest req) {

        LandmarkModifyDto landmarkModifyDto = LandmarkModifyDto.builder()
                .landmarkId(landmarkId)
                .name(req.getName())
                .description(req.getDescription())
                .address(req.getAddress())
                .homepage(req.getHomepage())
                .worldCountryCityRegionId(req.getWorldCountryCityRegionId())
                .build();

        return landmarkService.landmarkAlert(landmarkModifyDto);
    }

    @GetMapping("/landmarkAllList")
    public LandmarkAllListVo landmarkAllList() {
        return landmarkService.landmarkAllListSelect();
    }

}
