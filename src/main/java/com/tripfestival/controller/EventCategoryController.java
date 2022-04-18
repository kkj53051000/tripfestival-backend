package com.tripfestival.controller;

import com.tripfestival.service.EventCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventCategoryController {
    private final EventCategoryService eventCategoryService;
}
