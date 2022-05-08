package com.tripfestival.controller.hotspot;

import com.tripfestival.dto.hotspot.HotspotHotspotTypeModifyDto;
import com.tripfestival.request.hotspot.HotspotHotspotTypeModifyRequest;
import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.service.hotspot.HotspotService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotspotController {  // 인공 관광지

    private final HotspotService hotspotService;

    @PostMapping("/admin/hotspotProcess")
    public ResponseVo hotspotProcess(
            @RequestBody HotspotProcessRequest req) {

        return hotspotService.hotspotInsert(req);
    }

    @PostMapping("/admin/hotspotRemove/{id}")
    public ResponseVo hotspotRemove(@PathVariable Long hotspotId) {
        return hotspotService.hotspotDelete(hotspotId);
    }


    @GetMapping("/hotspotAllList")
    public HotspotAllListVo hotspotAllList() {
        return hotspotService.hotspotAllListSelect();
    }
}
