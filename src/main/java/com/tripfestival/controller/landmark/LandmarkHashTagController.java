package com.tripfestival.controller.landmark;

import com.tripfestival.service.landmark.LandmarkHashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkHashTagController {
    private final LandmarkHashTagService landmarkHashTagService;
}
