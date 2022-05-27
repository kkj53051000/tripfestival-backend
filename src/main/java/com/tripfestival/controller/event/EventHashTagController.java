package com.tripfestival.controller.event;

import com.tripfestival.request.event.EventHashTagProcessRequest;
import com.tripfestival.service.event.EventHashTagService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventHashTagAllListVo;
import com.tripfestival.vo.event.EventHashTagListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventHashTagController {
    private final EventHashTagService eventHashTagService;

    @PostMapping("/admin/eventHashTagProcess")
    public ResponseVo eventHashTagProcess(@RequestBody EventHashTagProcessRequest req) {
        return eventHashTagService.eventHashTagInsert(req);
    }

    @PostMapping("/admin/eventHashTagRemove/{id}")
    public ResponseVo eventHashTagRemove(@PathVariable("id") Long eventHashTagId) {
        return eventHashTagService.eventHashTagDelete(eventHashTagId);
    }

    @GetMapping("/eventHashTagList")
    public EventHashTagListVo eventHashTagList(@RequestParam("eventId") Long eventId) {
        return eventHashTagService.eventHashTagListSelect(eventId);
    }

    @GetMapping("/eventHashTagAllList")
    public EventHashTagAllListVo eventHashTagAllList() {
        return eventHashTagService.eventHashTagListAllSelect();
    }
}
