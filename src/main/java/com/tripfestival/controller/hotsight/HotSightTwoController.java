package com.tripfestival.controller.hotsight;

import com.tripfestival.dto.hotSight.HotSightTwoImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightTwoNameModifyDto;
import com.tripfestival.dto.hotSight.HotSightTwoProcessDto;
import com.tripfestival.request.hotsight.HotSightTwoNameModifyRequest;
import com.tripfestival.request.hotsight.HotSightTwoProcessRequest;
import com.tripfestival.service.hotsight.HotSightTwoService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightTwoController {  // 특별한 관광지 종류 2
    private final HotSightTwoService hotSightTwoService;

    @PostMapping("/hotSightTwoProcess")
    public ResponseVo hotSightTwoProcess(
            @RequestPart MultipartFile file,
            @RequestPart HotSightTwoProcessRequest req) {
        HotSightTwoProcessDto hotSightTwoProcessDto = HotSightTwoProcessDto.builder()
                .file(file)
                .name(req.getName())
                .build();

        return hotSightTwoService.hotSightTwoInsert(hotSightTwoProcessDto);
    }

    @PostMapping("/hotSightTwoRemove/{id}")
    public ResponseVo hotSightTwoRemove(@PathVariable("id") Long hotSightTwoId) {
        return hotSightTwoService.hotSightTwoDelete(hotSightTwoId);
    }

    @PostMapping("/hotSightTwoNameModify/{id}")
    public ResponseVo hotSightTwoNameModify(
            @PathVariable("id") Long hotSightTwoId,
            @RequestBody HotSightTwoNameModifyRequest req) {
        HotSightTwoNameModifyDto hotSightTwoNameModifyDto = new HotSightTwoNameModifyDto(hotSightTwoId, req);

        return hotSightTwoService.hostSightTwoNameAlert(hotSightTwoNameModifyDto);
    }

    @PostMapping("/hotSightTwoImgModify/{id}")
    public ResponseVo hotSightTwoImgModify(
            @PathVariable("id") Long hotSightTwoId,
            @RequestPart MultipartFile file) {
        HotSightTwoImgModifyDto hotSightTwoImgModifyDto = new HotSightTwoImgModifyDto(hotSightTwoId, file);

        return hotSightTwoService.hotSightTwoImgAlert(hotSightTwoImgModifyDto);
    }
}
