package com.tripfestival.controller.event;

import com.tripfestival.service.event.EventHashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventHashTagController {
    private final EventHashTagService eventHashTagService;


}
