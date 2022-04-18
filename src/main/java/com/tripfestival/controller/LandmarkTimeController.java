package com.tripfestival.controller;

import com.tripfestival.service.LandmarkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkTimeController {
    private final LandmarkTimeService landmarkTimeService;
}
