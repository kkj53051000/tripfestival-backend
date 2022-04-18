package com.tripfestival.controller;

import com.tripfestival.service.EventSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventSeasonController {
    private final EventSeasonService eventSeasonService;
}
