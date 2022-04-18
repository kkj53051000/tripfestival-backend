package com.tripfestival.controller;

import com.tripfestival.service.LandmarkFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkFeeController {
    private LandmarkFeeService landmarkFeeService;
}
