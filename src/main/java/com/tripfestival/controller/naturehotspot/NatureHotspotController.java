package com.tripfestival.controller.naturehotspot;

import com.tripfestival.dto.naturehotspot.NatureHotspotNatureHotspotTypeModifyDto;
import com.tripfestival.request.naturehotspot.NatureHotspotNatureHotspotTypeModifyRequest;
import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
import com.tripfestival.service.naturehotspot.NatureHotspotService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NatureHotspotController {  // 자연관광지
    private final NatureHotspotService natureHotspotService;

    @PostMapping("/naturehotspotprocess")
    public ResponseVo natureHotspotProcess(@RequestBody NatureHotspotProcessRequest req) {
        return natureHotspotService.natureHotspotInsert(req);
    }

    @PostMapping("/naturehotspotremove/{id}")
    public ResponseVo natureHotspotRemove(@PathVariable("id") Long natureHotspotId) {
        return natureHotspotService.natureHotspotDelete(natureHotspotId);
    }

    @PostMapping("/naturehotspotnaturehotspottypemodify/{id}")
    public ResponseVo natureHotspotNatureHotspotTypeModify(
            @PathVariable("id") Long natureHotspotId,
            @RequestBody NatureHotspotNatureHotspotTypeModifyRequest req) {

        NatureHotspotNatureHotspotTypeModifyDto natureHotspotNatureHotspotTypeModifyDto =
                new NatureHotspotNatureHotspotTypeModifyDto(natureHotspotId, req);

        return natureHotspotService.natureHotspotNatureHotspotTypeAlert(natureHotspotNatureHotspotTypeModifyDto);
    }
}
