package com.tripfestival.controller;

import com.tripfestival.service.EventReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventReviewController {
    private final EventReviewService eventReviewService;
}
