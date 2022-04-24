package com.tripfestival.controller.naturehotspot;

import com.tripfestival.dto.naturehotspot.NatureHotspotTypeDto;
import com.tripfestival.request.naturehotspot.NatureHotspotTypeRequest;
import com.tripfestival.service.naturehotspot.NatureHotspotTypeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class NatureHotspotTypeController {  // 자연관광지 타입
    private final NatureHotspotTypeService natureHotspotTypeService;

    @PostMapping("/naturehotspottypeprocess")
    public ResponseVo natureHotspotTypeProcess(
            @RequestPart MultipartFile file,
            @RequestPart("value") NatureHotspotTypeRequest req) {

        NatureHotspotTypeDto natureHotspotTypeDto = NatureHotspotTypeDto.builder()
                .name(req.getName())
                .file(file)
                .build();

        return natureHotspotTypeService.natureHotspotTypeInsert(natureHotspotTypeDto);
    }

    @PostMapping("/naturehotspottyperemove/{id}")
    public ResponseVo natureHotspotTypeRemove(@PathVariable("id") Long natureHotspotTypeId) {
        return natureHotspotTypeService.natureHotspotTypeDelete(natureHotspotTypeId);
    }
}
