package com.tripfestival.controller;

import com.tripfestival.dto.HotspotProcessDto;
import com.tripfestival.request.HotspotProcessRequest;
import com.tripfestival.service.HotspotService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotspotController {
    private final HotspotService hotspotService;

    @PostMapping("/hotspotprocess")
    public ResponseVo hotspotProcess(
            @RequestPart MultipartFile file,
            @RequestPart(name = "value") HotspotProcessRequest req) {

        HotspotProcessDto hotspotProcessDto = new HotspotProcessDto(file, req.getName(), req.getWorldCountryCityId());

        return hotspotService.hotspotInsert(hotspotProcessDto);
    }

    @PostMapping("/hotspotremove/{id}")
    public ResponseVo hotspotRemove(@PathVariable Long hotspotId) {
        return hotspotService.hotspotDelete(hotspotId);
    }
}
