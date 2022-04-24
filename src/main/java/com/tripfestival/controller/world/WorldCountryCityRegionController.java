package com.tripfestival.controller.world;

import com.tripfestival.service.world.WorldCountryCityRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorldCountryCityRegionController {
    private WorldCountryCityRegionService worldCountryCityRegionService;
}
