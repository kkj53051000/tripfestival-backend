package com.tripfestival.controller;

import com.tripfestival.dto.HotSightTwoProcessDto;
import com.tripfestival.repository.HotSightTwoProcessRequest;
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
}
