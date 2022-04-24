package com.tripfestival.controller;

import com.tripfestival.dto.HotSightOneImgModifyDto;
import com.tripfestival.dto.HotSightOneNameModifyDto;
import com.tripfestival.dto.HotSightOneProcessDto;
import com.tripfestival.request.HotSightOneNameModifyRequest;
import com.tripfestival.request.HotSightOneProcessRequest;
import com.tripfestival.service.HotSightOneService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightOneController {
    private final HotSightOneService hotSightOneService;

    @PostMapping("/hotsightoneprocess")
    public ResponseVo hotSightOneProcess(
            @RequestPart MultipartFile file,
            @RequestPart HotSightOneProcessRequest req) {
        HotSightOneProcessDto hotSightOneProcessDto = new HotSightOneProcessDto(file, req);

        return hotSightOneService.hotSightOneInsert(hotSightOneProcessDto);
    }

    @PostMapping("/hotsightoneremove/{id}")
    public ResponseVo hotSightOneRemove(@PathVariable("id") Long hotSightOneId) {
        return hotSightOneService.hotSightOneDelete(hotSightOneId);
    }

    @PostMapping("/hotsightonenamemodify/{id}")
    public ResponseVo hotSightOneNameModify(
            @PathVariable("id") Long hotSightOneId,
            @RequestBody HotSightOneNameModifyRequest req) {

        HotSightOneNameModifyDto hotSightOneNameModifyDto = new HotSightOneNameModifyDto(hotSightOneId, req);

        return hotSightOneService.hotSightOneNameAlert(hotSightOneNameModifyDto);
    }

    @PostMapping("/hotsightoneimgmodify/{id}")
    public ResponseVo hotSightOneImgModify(
            @PathVariable("id") Long hotSightOneId,
            @RequestPart MultipartFile file) {
        HotSightOneImgModifyDto hotSightOneImgModifyDto = new HotSightOneImgModifyDto(hotSightOneId, file);

        return hotSightOneService.hotSightOneImgAlert(hotSightOneImgModifyDto);
    }
}
