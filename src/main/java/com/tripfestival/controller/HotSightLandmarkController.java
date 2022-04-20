package com.tripfestival.controller;

import com.tripfestival.service.HotSightLandmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightLandmarkController {
    private final HotSightLandmarkService hotSightLandmarkService;
}
