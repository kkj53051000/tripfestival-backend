package com.tripfestival.controller;

import com.tripfestival.service.HotspotTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotspotTypeController {
    private final HotspotTypeService hotspotTypeService;
}
