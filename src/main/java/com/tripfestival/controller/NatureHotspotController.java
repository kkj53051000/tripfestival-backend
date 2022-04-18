package com.tripfestival.controller;

import com.tripfestival.service.NatureHotspotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NatureHotspotController {
    private final NatureHotspotService natureHotspotService;
}
