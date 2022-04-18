package com.tripfestival.controller;

import com.tripfestival.service.LandmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkController {
    private final LandmarkService landmarkService;
}
