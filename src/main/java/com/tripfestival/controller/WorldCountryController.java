package com.tripfestival.controller;

import com.tripfestival.service.WorldCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorldCountryController {
    private final WorldCountryService worldCountryService;
}
