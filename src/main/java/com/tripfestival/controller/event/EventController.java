package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventModifyDto;
import com.tripfestival.request.event.EventModifyRequest;
import com.tripfestival.request.event.EventProcessRequest;
import com.tripfestival.service.event.EventService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    @PostMapping("/eventProcess")
    public ResponseVo eventProcess(@RequestBody EventProcessRequest req) {
        return eventService.eventInsert(req);
    }

    @PostMapping("/eventRemove/{id}")
    public ResponseVo eventRemove(@PathVariable("id") Long eventId) {
        return eventService.eventDelete(eventId);
    }

    @PostMapping("/eventModify/{id}")
    public ResponseVo eventModify(
            @PathVariable("id") Long eventId,
            @RequestBody EventModifyRequest req) {
        EventModifyDto eventModifyDto = new EventModifyDto(eventId, req);

        return eventService.eventAlert(eventModifyDto);
    }
}
