package com.tripfestival.controller.naturehotspot;

import com.tripfestival.dto.naturehotspot.NatureHotspotTypeDto;
import com.tripfestival.request.naturehotspot.NatureHotspotTypeRequest;
import com.tripfestival.service.naturehotspot.NatureHotspotTypeService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotTypeAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NatureHotspotTypeController {  // 자연관광지 타입
    private final NatureHotspotTypeService natureHotspotTypeService;

    @PostMapping("/admin/natureHotspotTypeProcess")
    public ResponseVo natureHotspotTypeProcess(
            @RequestPart MultipartFile file,
            @RequestPart("value") NatureHotspotTypeRequest req) {

        NatureHotspotTypeDto natureHotspotTypeDto = NatureHotspotTypeDto.builder()
                .name(req.getName())
                .file(file)
                .build();

        return natureHotspotTypeService.natureHotspotTypeInsert(natureHotspotTypeDto);
    }

    @PostMapping("/admin/natureHotspotTypeRemove/{id}")
    public ResponseVo natureHotspotTypeRemove(@PathVariable("id") Long natureHotspotTypeId) {
        return natureHotspotTypeService.natureHotspotTypeDelete(natureHotspotTypeId);
    }

    @GetMapping("/natureHotspotTypeAllList")
    public NatureHotspotTypeAllListVo natureHotspotTypeAllList() {
        return natureHotspotTypeService.natureHotspotTypeAllSelect();
    }
}
