package com.tripfestival.controller;

import com.tripfestival.service.EventFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventFeeController {
    private final EventFeeService eventFeeService;
}
