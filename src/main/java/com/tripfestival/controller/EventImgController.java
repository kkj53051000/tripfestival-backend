package com.tripfestival.controller;

import com.tripfestival.service.EventImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventImgController {
    private final EventImgService eventImgService;
}
