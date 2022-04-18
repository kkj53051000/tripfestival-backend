package com.tripfestival.controller;

import com.tripfestival.service.WorldCountryCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorldCountryCityController {
    private final WorldCountryCityService worldCountryCityService;
}
