package com.tripfestival.controller;

import com.tripfestival.service.LandmarkImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkImgController {
    private final LandmarkImgService landmarkImgService;
}
