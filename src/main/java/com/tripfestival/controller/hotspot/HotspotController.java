package com.tripfestival.controller.hotspot;

import com.tripfestival.dto.hotspot.HotspotHotspotTypeModifyDto;
import com.tripfestival.dto.hotspot.HotspotListDto;
import com.tripfestival.request.hotspot.HotspotHotspotTypeModifyRequest;
import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.service.hotspot.HotspotService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotAllListVo;
import com.tripfestival.vo.hotspot.HotspotListVo;
import com.tripfestival.vo.hotspot.HotspotNameVo;
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
    public ResponseVo hotspotRemove(@PathVariable("id") Long hotspotId) {
        return hotspotService.hotspotDelete(hotspotId);
    }

    @GetMapping("/hotspotList")
    public HotspotListVo hotspotList(@RequestParam Long hotspotTypeId,
                                     @RequestParam Long worldCountryCityId,
                                     @RequestParam Long worldCountryCityRegionId) {

        HotspotListDto hotspotListDto = HotspotListDto.builder()
                .hotspotTypeId(hotspotTypeId)
                .worldCountryCityId(worldCountryCityId)
                .worldCountryCityRegionId(worldCountryCityRegionId)
                .build();

        return hotspotService.hotspotListSelect(hotspotListDto);
    }

    @GetMapping("/hotspotAllList")
    public HotspotAllListVo hotspotAllList() {
        return hotspotService.hotspotAllListSelect();
    }

    @GetMapping("/hotspotName/{id}")
    public HotspotNameVo hotspotName(@PathVariable("id") Long hotspotId) {
        return hotspotService.hotspotNameSelect(hotspotId);
    }
}
