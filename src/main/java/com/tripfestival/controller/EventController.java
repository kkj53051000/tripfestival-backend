package com.tripfestival.controller;

import com.tripfestival.dto.EventModifyDto;
import com.tripfestival.request.EventModifyRequest;
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

    @PostMapping("/eventmodify/{id}")
    public ResponseVo eventModify(
            @PathVariable("id") Long eventId,
            @RequestBody EventModifyRequest req) {
        EventModifyDto eventModifyDto = EventModifyDto.builder()
                .eventId(eventId)
                .name(req.getName())
                .description(req.getDescription())
                .address(req.getAddress())
                .visitor(req.getVisitor())
                .inout(req.getInout())
                .worldCountryCityId(req.getWorldCountryCityId())
                .eventCategoryId(req.getEventCategoryId())
                .eventSeasonId(req.getEventSeasonId())
                .build();

        return eventService.eventAlert(eventModifyDto);
    }
}
