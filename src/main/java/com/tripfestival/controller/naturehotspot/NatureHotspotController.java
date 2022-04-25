package com.tripfestival.controller.naturehotspot;

import com.tripfestival.dto.naturehotspot.NatureHotspotImgModifyDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotNatureHotspotTypeModifyDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotProcessDto;
import com.tripfestival.request.naturehotspot.NatureHotspotNatureHotspotTypeModifyRequest;
import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
import com.tripfestival.service.naturehotspot.NatureHotspotService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class NatureHotspotController {  // 자연관광지
    private final NatureHotspotService natureHotspotService;

    @PostMapping("/natureHotspotProcess")
    public ResponseVo natureHotspotProcess(
            @RequestPart MultipartFile file,
            @RequestPart NatureHotspotProcessRequest req) {

        NatureHotspotProcessDto natureHotspotProcessDto
                = new NatureHotspotProcessDto(file, req);

        return natureHotspotService.natureHotspotInsert(natureHotspotProcessDto);
    }

    @PostMapping("/natureHotspotRemove/{id}")
    public ResponseVo natureHotspotRemove(@PathVariable("id") Long natureHotspotId) {
        return natureHotspotService.natureHotspotDelete(natureHotspotId);
    }

    @PostMapping("/natureHotspotNatureHotspotTypeModify/{id}")
    public ResponseVo natureHotspotNatureHotspotTypeModify(
            @PathVariable("id") Long natureHotspotId,
            @RequestBody NatureHotspotNatureHotspotTypeModifyRequest req) {

        NatureHotspotNatureHotspotTypeModifyDto natureHotspotNatureHotspotTypeModifyDto =
                new NatureHotspotNatureHotspotTypeModifyDto(natureHotspotId, req);

        return natureHotspotService.natureHotspotNatureHotspotTypeAlert(natureHotspotNatureHotspotTypeModifyDto);
    }

    @PostMapping("/natureHotspotImgModify/{id}")
    public ResponseVo natureHotspotImgModify(
            @PathVariable("id") Long natureHotspotId,
            @RequestPart MultipartFile file) {

        NatureHotspotImgModifyDto natureHotspotImgModifyDto
                = new NatureHotspotImgModifyDto(natureHotspotId, file);

        return natureHotspotService.natureHotspotImgAlert(natureHotspotImgModifyDto);
    }

    @GetMapping("/natureHotspotList")
    public NatureHotspotListVo natureHotspotList(@RequestParam Long natureHotspotTypeId) {
        return natureHotspotService.natureHotspotListSelect(natureHotspotTypeId);
    }
}
