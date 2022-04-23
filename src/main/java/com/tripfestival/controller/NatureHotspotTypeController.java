package com.tripfestival.controller;

import com.tripfestival.service.NatureHotspotTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NatureHotspotTypeController {
    private final NatureHotspotTypeService natureHotspotTypeService;
}
