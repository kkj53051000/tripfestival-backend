package com.tripfestival.controller.hotspot;

import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.service.hotspot.HotspotService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotspotController {
    private final HotspotService hotspotService;

    @PostMapping("/hotspotprocess")
    public ResponseVo hotspotProcess(
            @RequestPart(name = "value") HotspotProcessRequest req) {

        return hotspotService.hotspotInsert(req);
    }

    @PostMapping("/hotspotremove/{id}")
    public ResponseVo hotspotRemove(@PathVariable Long hotspotId) {
        return hotspotService.hotspotDelete(hotspotId);
    }
}
