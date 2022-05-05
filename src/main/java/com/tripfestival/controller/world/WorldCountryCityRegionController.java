package com.tripfestival.controller.world;

import com.tripfestival.dto.world.WorldCountryCityRegionNameModifyDto;
import com.tripfestival.dto.world.WorldCountryCityRegionProcessDto;
import com.tripfestival.request.world.WorldCountryCityRegionListRequest;
import com.tripfestival.request.world.WorldCountryCityRegionNameModifyRequest;
import com.tripfestival.request.world.WorldCountryCityRegionProcessRequest;
import com.tripfestival.service.world.WorldCountryCityRegionService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.WorldCountryCityRegionListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryCityRegionController {
    private final WorldCountryCityRegionService worldCountryCityRegionService;

    @PostMapping("/admin/worldCountryCityRegionProcess")
    public ResponseVo worldCountryCityRegionProcess(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "value") WorldCountryCityRegionProcessRequest req) {

        WorldCountryCityRegionProcessDto worldCountryCityRegionProcessDto
                = new WorldCountryCityRegionProcessDto(file, req);

        return worldCountryCityRegionService.worldCountryCityRegionInsert(worldCountryCityRegionProcessDto);
    }

    @PostMapping("/admin/worldCountryCityRegionRemove/{id}")
    public ResponseVo worldCountryCityRegionRemove(@PathVariable("id") Long worldCountryCityId) {
        return worldCountryCityRegionService.worldCountryCityRegionDelete(worldCountryCityId);
    }

    @PostMapping("/admin/worldCountryCityRegionNameModify/{id}")
    public ResponseVo worldCountryCityRegionNameModify(
            @PathVariable("id") Long worldCountryCityId,
            @RequestBody WorldCountryCityRegionNameModifyRequest req) {

        WorldCountryCityRegionNameModifyDto worldCountryCityRegionNameModifyDto
                = new WorldCountryCityRegionNameModifyDto(worldCountryCityId, req);

        return worldCountryCityRegionService.worldCountryCityRegionNameAlert(worldCountryCityRegionNameModifyDto);
    }

    @GetMapping("/worldCountryCityRegionList")
    public WorldCountryCityRegionListVo worldCountryCityRegionList() {
        return worldCountryCityRegionService.worldCountryCityRegionListSelect();
    }
}
