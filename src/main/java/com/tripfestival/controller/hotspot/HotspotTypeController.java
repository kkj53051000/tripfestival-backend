package com.tripfestival.controller.hotspot;

import com.tripfestival.dto.hotspot.HotspotTypeNameModifyDto;
import com.tripfestival.dto.hotspot.HotspotTypeProcessDto;
import com.tripfestival.request.hotspot.HotspotTypeNameModifyRequest;
import com.tripfestival.request.hotspot.HotspotTypeProcessRequest;
import com.tripfestival.service.hotspot.HotspotTypeService;
import com.tripfestival.vo.hotspot.HotspotTypeAllListVo;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotTypeNameVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotspotTypeController { // 인공 관광지 종류

    private final HotspotTypeService hotspotTypeService;

    @PostMapping("/admin/hotspotTypeProcess")
    public ResponseVo hotspotTypeProcess(
            @RequestPart MultipartFile file,
            @RequestPart("value") HotspotTypeProcessRequest req) {

        HotspotTypeProcessDto hotspotTypeProcessDto = HotspotTypeProcessDto.builder()
                .file(file)
                .name(req.getName())
                .build();

        return hotspotTypeService.hotspotTypeInsert(hotspotTypeProcessDto);
    }

    @PostMapping("/admin/hotspotTypeRemove/{id}")
    public ResponseVo hotspotTypeRemove(@PathVariable("id") Long hotspotTypeId) {
        return hotspotTypeService.hotspotTypeDelete(hotspotTypeId);
    }

    @PostMapping("/admin/hotspotTypeNameModify/{id}")
    public ResponseVo hotspotTypeNameModify(
            @PathVariable("id") Long hotspotTypeId,
            @RequestBody HotspotTypeNameModifyRequest req) {
        HotspotTypeNameModifyDto hotspotTypeNameModifyDto = new HotspotTypeNameModifyDto(hotspotTypeId, req);

        return hotspotTypeService.hotspotTypeNameAlert(hotspotTypeNameModifyDto);
    }

    @GetMapping("/hotspotTypeAllList")
    public HotspotTypeAllListVo hotspotTypeAllList() {
        return hotspotTypeService.hotspotTypeAllListSelect();
    }

    @GetMapping("/hotspotTypeName/{id}")
    public HotspotTypeNameVo hotspotTypeName(@PathVariable("id") Long hotspotTypeId) {
        return hotspotTypeService.HotspotTypeNameSelect(hotspotTypeId);
    }
}
