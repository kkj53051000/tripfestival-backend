package com.tripfestival.controller.world;

import com.tripfestival.dto.world.WorldCountryModifyDto;
import com.tripfestival.request.world.WorldCountryModifyRequest;
import com.tripfestival.request.world.WorldCountryProcessRequest;
import com.tripfestival.service.world.WorldCountryService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryNameListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryController {
    private final WorldCountryService worldCountryService;

    @PostMapping("/admin/worldCountryProcess")
    public ResponseVo worldCountryProcess(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "value") WorldCountryProcessRequest req) {

        return worldCountryService.worldCountryInsert(file, req);
    }

    @PostMapping("/admin/worldCountryRemove/{id}")
    public ResponseVo worldCountryRemove(@PathVariable("id") Long worldCountryId) {
        return worldCountryService.worldCountryDelete(worldCountryId);
    }

    @PostMapping("/admin/worldCountryModify/{id}")
    public ResponseVo worldCountryModify(
            @PathVariable("id") Long worldCountryId,
            @RequestBody WorldCountryModifyRequest req) {
        WorldCountryModifyDto worldCountryModifyDto = new WorldCountryModifyDto(worldCountryId, req);

        return worldCountryService.worldCountryAlert(worldCountryModifyDto);
    }

    @GetMapping("/worldCountryNameList")
    public WorldCountryNameListVo worldCountryNameList() {
        return worldCountryService.worldCountryNameListSelect();
    }
}
