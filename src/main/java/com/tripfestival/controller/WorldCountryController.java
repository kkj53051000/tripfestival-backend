package com.tripfestival.controller;

import com.tripfestival.request.WorldCountryRequest;
import com.tripfestival.service.WorldCountryService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryController {
    private final WorldCountryService worldCountryService;

    @PostMapping("/worldcountryprocess")
    public ResponseVo worldCountryProcess(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "value", required = false) WorldCountryRequest req) {


        return worldCountryService.worldCountryInsert(file, req);
    }

    @PostMapping("/worldcountryremove")
    public ResponseVo worldCountryRemove(@RequestParam Long worldCountryId) {
        return worldCountryService.worldCountryDelete(worldCountryId);
    }
}
