package com.tripfestival.controller;

import com.tripfestival.service.LandmarkReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkReviewController {
    private final LandmarkReviewService landmarkReviewService;
}
