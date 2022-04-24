package com.tripfestival.controller.hotspot;

import com.tripfestival.dto.hotspot.HotspotTypeProcessDto;
import com.tripfestival.request.hotspot.HotspotTypeProcessRequest;
import com.tripfestival.service.hotspot.HotspotTypeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotspotTypeController {
    private final HotspotTypeService hotspotTypeService;

    @PostMapping("/hotspottypeprocess")
    public ResponseVo hotspotTypeProcess(
            @RequestPart MultipartFile file,
            @RequestPart("value")HotspotTypeProcessRequest req) {

        HotspotTypeProcessDto hotspotTypeProcessDto = HotspotTypeProcessDto.builder()
                .file(file)
                .name(req.getName())
                .build();

        return hotspotTypeService.hotspotTypeInsert(hotspotTypeProcessDto);
    }

    @PostMapping("/hotspottyperemove/{id}")
    public ResponseVo hotspotTypeRemove(@PathVariable("id") Long hotspotTypeId) {
        return hotspotTypeService.hotspotTypeRemove(hotspotTypeId);
    }
}
