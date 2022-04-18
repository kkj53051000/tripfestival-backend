package com.tripfestival.controller;

import com.tripfestival.service.EventTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventTimeController {
    private final EventTimeService eventTimeService;
}
