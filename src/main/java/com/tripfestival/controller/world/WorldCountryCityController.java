package com.tripfestival.controller.world;

import com.tripfestival.dto.world.WorldCountryCityNameModifyDto;
import com.tripfestival.request.world.WorldCountryCityNameModifyRequest;
import com.tripfestival.request.world.WorldCountryCityProcessRequest;
import com.tripfestival.service.world.WorldCountryCityService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryCityNameListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryCityController {
    private final WorldCountryCityService worldCountryCityService;

    @PostMapping("/worldCountryCityProcess")
    public ResponseVo worldCountryCityProcess(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "value", required = false) WorldCountryCityProcessRequest req) {
        return worldCountryCityService.worldCountryCityInsert(file, req);
    }

    @PostMapping("/worldCountryCityRemmove/{id}")
    public ResponseVo worldCountryCityRemove(@PathVariable("id") Long worldCountryCityId) {
        return worldCountryCityService.worldCountryCityDelete(worldCountryCityId);
    }

    @PostMapping("/worldCountryCityNameModify/{id}")
    public ResponseVo worldCountryCityNameModify(
            @PathVariable("id") Long worldCountryCityId,
            WorldCountryCityNameModifyRequest req) {

        WorldCountryCityNameModifyDto worldCountryCityNameModifyDto
                = new WorldCountryCityNameModifyDto(worldCountryCityId, req);

        return worldCountryCityService.worldCountryCityNameAlert(worldCountryCityNameModifyDto);
    }

    @GetMapping("/worldCountryCityNameList")
    public WorldCountryCityNameListVo worldCountryCityNameList() {
        return worldCountryCityService.worldCountryCityNameList();
    }
}
