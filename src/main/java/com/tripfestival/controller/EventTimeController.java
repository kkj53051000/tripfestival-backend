package com.tripfestival.controller;

import com.tripfestival.request.EventTimeProcessRequest;
import com.tripfestival.service.EventTimeService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventTimeController {
    private final EventTimeService eventTimeService;

    @PostMapping("/eventtimeprocess")
    public ResponseVo eventTimeProcess(@RequestBody EventTimeProcessRequest req) {
        return eventTimeService.eventTimeInsert(req);
    }

    @PostMapping("/eventtimeremove/{id}")
    public ResponseVo eventTimeRemove(@PathVariable("id") Long eventTimeId) {
        return eventTimeService.eventTimeDelete(eventTimeId);
    }
}
