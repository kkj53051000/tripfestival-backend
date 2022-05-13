package com.tripfestival.controller.world;

import com.tripfestival.dto.world.WorldCountryCityNameModifyDto;
import com.tripfestival.request.world.WorldCountryCityNameModifyRequest;
import com.tripfestival.request.world.WorldCountryCityProcessRequest;
import com.tripfestival.service.world.WorldCountryCityService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.world.WorldCountryCityNameListVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryCityController {
    private final WorldCountryCityService worldCountryCityService;

    @PostMapping("/admin/worldCountryCityProcess")
    public ResponseVo worldCountryCityProcess(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "value") WorldCountryCityProcessRequest req) {
        return worldCountryCityService.worldCountryCityInsert(file, req);
    }

    @PostMapping("/admin/worldCountryCityRemove/{id}")
    public ResponseVo worldCountryCityRemove(@PathVariable("id") Long worldCountryCityId) {
        return worldCountryCityService.worldCountryCityDelete(worldCountryCityId);
    }

    @PostMapping("/admin/worldCountryCityNameModify/{id}")
    public ResponseVo worldCountryCityNameModify(
            @PathVariable("id") Long worldCountryCityId,
            @RequestBody WorldCountryCityNameModifyRequest req) {

        WorldCountryCityNameModifyDto worldCountryCityNameModifyDto
                = new WorldCountryCityNameModifyDto(worldCountryCityId, req);

        return worldCountryCityService.worldCountryCityNameAlert(worldCountryCityNameModifyDto);
    }

    @GetMapping("/worldCountryCityNameList")
    public WorldCountryCityNameListVo worldCountryCityNameList() {
        return worldCountryCityService.worldCountryCityNameList();
    }
}
