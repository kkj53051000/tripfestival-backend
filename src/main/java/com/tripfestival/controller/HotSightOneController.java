package com.tripfestival.controller;

import com.tripfestival.dto.HotSightOneProcessDto;
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
        HotSightOneProcessDto hotSightOneProcessDto = HotSightOneProcessDto.builder()
                .file(file)
                .name(req.getName())
                .build();

        return hotSightOneService.hotSightOneInsert(hotSightOneProcessDto);
    }

    @PostMapping("/hotsightoneremove/{id}")
    public ResponseVo hotSightOneRemove(@PathVariable("id") Long hotSightOneId) {
        return hotSightOneService.hotSightOneDelete(hotSightOneId);
    }
}
