package com.tripfestival.controller;

import com.tripfestival.service.HotspotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotspotController {
    private final HotspotService hotspotService;
}
