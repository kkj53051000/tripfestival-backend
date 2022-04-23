package com.tripfestival.controller;

import com.tripfestival.request.NatureHotspotProcessRequest;
import com.tripfestival.service.NatureHotspotService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NatureHotspotController {
    private final NatureHotspotService natureHotspotService;

    @PostMapping("/naturehotspotprocess")
    public ResponseVo natureHotspotProcess(@RequestBody NatureHotspotProcessRequest req) {
        return natureHotspotService.natureHotspotInsert(req);
    }

    @PostMapping("/naturehotspotremove/{id}")
    public ResponseVo natureHotspotRemove(@PathVariable("id") Long natureHotspotId) {
        return natureHotspotService.natureHotspotDelete(natureHotspotId);
    }
}
