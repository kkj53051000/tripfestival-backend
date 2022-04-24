package com.tripfestival.controller.hotspot;

import com.tripfestival.dto.hotspot.HotspotHotspotTypeModifyDto;
import com.tripfestival.request.hotspot.HotspotHotspotTypeModifyRequest;
import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.service.hotspot.HotspotService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotspotController {  // 인공 관광지

    private final HotspotService hotspotService;

    @PostMapping("/hotspotProcess")
    public ResponseVo hotspotProcess(
            @RequestPart(name = "value") HotspotProcessRequest req) {

        return hotspotService.hotspotInsert(req);
    }

    @PostMapping("/hotspotRemove/{id}")
    public ResponseVo hotspotRemove(@PathVariable Long hotspotId) {
        return hotspotService.hotspotDelete(hotspotId);
    }

    @PostMapping("/hotspotHotspotTypeModify/{id}")
    public ResponseVo hotspotHotspotTypeModify(
            @PathVariable Long hotspotId,
            @RequestBody HotspotHotspotTypeModifyRequest req) {

        HotspotHotspotTypeModifyDto hotspotHotspotTypeModifyDto =
                new HotspotHotspotTypeModifyDto(hotspotId, req);

        return hotspotService.hotspotHotspotTypeAlert(hotspotHotspotTypeModifyDto);
    }
}
