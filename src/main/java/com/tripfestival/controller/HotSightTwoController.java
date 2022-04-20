package com.tripfestival.controller;

import com.tripfestival.service.HotSightTwoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightTwoController {
    private final HotSightTwoService hotSightTwoService;
}
