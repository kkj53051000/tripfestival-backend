package com.tripfestival.controller;

import com.tripfestival.request.EventProcessRequest;
import com.tripfestival.service.EventService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    @PostMapping("/eventprocess")
    public ResponseVo eventProcess(@RequestBody EventProcessRequest req) {
        return eventService.eventInsert(req);
    }

    @PostMapping("/eventremove/{id}")
    public ResponseVo eventRemove(@PathVariable("id") Long eventId) {
        return eventService.eventDelete(eventId);
    }
}
