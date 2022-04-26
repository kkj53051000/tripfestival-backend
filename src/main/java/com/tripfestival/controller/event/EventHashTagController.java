package com.tripfestival.controller.event;

import com.tripfestival.request.event.EventHashTagProcessRequest;
import com.tripfestival.service.event.EventHashTagService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventHashTagController {
    private final EventHashTagService eventHashTagService;

    @PostMapping("/eventHashTagProcess")
    public ResponseVo eventHashTagProcess(@RequestBody EventHashTagProcessRequest req) {
        return eventHashTagService.eventHashTagInsert(req);
    }

    @PostMapping("/eventHashTagRemove/{id}")
    public ResponseVo eventHashTagRemove(@PathVariable("id") Long eventHashTagId) {
        return eventHashTagService.eventHashTagDelete(eventHashTagId);
    }
}
