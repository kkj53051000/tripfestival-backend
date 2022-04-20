package com.tripfestival.controller;

import com.tripfestival.service.HotSightOneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightOneController {
    private final HotSightOneService hotSightOneService;
}
