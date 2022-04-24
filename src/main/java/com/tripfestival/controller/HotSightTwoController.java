package com.tripfestival.controller;

import com.tripfestival.dto.HotSightTwoImgModifyDto;
import com.tripfestival.dto.HotSightTwoNameModifyDto;
import com.tripfestival.dto.HotSightTwoProcessDto;
import com.tripfestival.request.HotSightTwoNameModifyRequest;
import com.tripfestival.request.HotSightTwoProcessRequest;
import com.tripfestival.service.HotSightTwoService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightTwoController {
    private final HotSightTwoService hotSightTwoService;

    @PostMapping("/hotsighttwoprocess")
    public ResponseVo hotSightTwoProcess(
            @RequestPart MultipartFile file,
            @RequestPart HotSightTwoProcessRequest req) {
        HotSightTwoProcessDto hotSightTwoProcessDto = HotSightTwoProcessDto.builder()
                .file(file)
                .name(req.getName())
                .build();

        return hotSightTwoService.hotSightTwoInsert(hotSightTwoProcessDto);
    }

    @PostMapping("/hotsighttworemove/{id}")
    public ResponseVo hotSightTwoRemove(@PathVariable("id") Long hotSightTwoId) {
        return hotSightTwoService.hotSightTwoDelete(hotSightTwoId);
    }

    @PostMapping("/hotsighttwonamemodify/{id}")
    public ResponseVo hotSightTwoNameModify(
            @PathVariable("id") Long hotSightTwoId,
            @RequestBody HotSightTwoNameModifyRequest req) {
        HotSightTwoNameModifyDto hotSightTwoNameModifyDto = new HotSightTwoNameModifyDto(hotSightTwoId, req);

        return hotSightTwoService.hostSightTwoNameAlert(hotSightTwoNameModifyDto);
    }

    @PostMapping("/hotsighttwoimgmodify/{id}")
    public ResponseVo hotSightTwoImgModify(
            @PathVariable("id") Long hotSightTwoId,
            @RequestPart MultipartFile file) {
        HotSightTwoImgModifyDto hotSightTwoImgModifyDto = new HotSightTwoImgModifyDto(hotSightTwoId, file);

        return hotSightTwoService.hotSightTwoImgAlert(hotSightTwoImgModifyDto);
    }
}
