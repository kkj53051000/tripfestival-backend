package com.tripfestival.controller.world;

import com.tripfestival.dto.world.WorldCountryModifyDto;
import com.tripfestival.request.world.WorldCountryModifyRequest;
import com.tripfestival.request.world.WorldCountryProcessRequest;
import com.tripfestival.service.world.WorldCountryService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryController {
    private final WorldCountryService worldCountryService;

    @PostMapping("/worldCountryProcess")
    public ResponseVo worldCountryProcess(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "value", required = false) WorldCountryProcessRequest req) {

        return worldCountryService.worldCountryInsert(file, req);
    }

    @PostMapping("/worldCountryRemove/{id}")
    public ResponseVo worldCountryRemove(@PathVariable("id") Long worldCountryId) {
        return worldCountryService.worldCountryDelete(worldCountryId);
    }

    @PostMapping("/worldCountryModify/{id}")
    public ResponseVo worldCountryModify(
            @PathVariable("id") Long worldCountryId,
            @RequestBody WorldCountryModifyRequest req) {
        WorldCountryModifyDto worldCountryModifyDto = new WorldCountryModifyDto(worldCountryId, req);

        return worldCountryService.worldCountryAlert(worldCountryModifyDto);
    }
}
