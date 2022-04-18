package com.tripfestival.controller;

import com.tripfestival.service.LandmarkReviewImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LandmarkReviewImgController {
    private final LandmarkReviewImgService landmarkReviewImgService;
}
