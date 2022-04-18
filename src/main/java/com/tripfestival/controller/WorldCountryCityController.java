package com.tripfestival.controller;

import com.tripfestival.request.WorldCountryCityProcessRequest;
import com.tripfestival.service.WorldCountryCityService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryCityController {
    private final WorldCountryCityService worldCountryCityService;

    @PostMapping("/worldcountrycityprocess")
    public ResponseVo worldCountryCityProcess(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "value", required = false) WorldCountryCityProcessRequest req) {
        return worldCountryCityService.worldCountryCityInsert(file, req);
    }

    @PostMapping("/worldcountrycityremmove/{id}")
    public ResponseVo worldCountryCityRemove(@PathVariable("id") Long worldCountryCityId) {
        return worldCountryCityService.worldCountryCityDelete(worldCountryCityId);
    }
}
