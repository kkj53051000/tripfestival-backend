package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventTimeModifyDto;
import com.tripfestival.request.event.EventTimeModifyRequest;
import com.tripfestival.request.event.EventTimeProcessRequest;
import com.tripfestival.service.event.EventTimeService;
import com.tripfestival.vo.EventTimeListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventTimeController {
    private final EventTimeService eventTimeService;

    @PostMapping("/eventTimeProcess")
    public ResponseVo eventTimeProcess(@RequestBody EventTimeProcessRequest req) {
        return eventTimeService.eventTimeInsert(req);
    }

    @PostMapping("/eventTimeRemove/{id}")
    public ResponseVo eventTimeRemove(@PathVariable("id") Long eventTimeId) {
        return eventTimeService.eventTimeDelete(eventTimeId);
    }

    @PostMapping("/eventTimeModify/{id}")
    public ResponseVo eventTimeModify(
            @PathVariable("id") Long eventTimeId,
            @RequestBody EventTimeModifyRequest req) {

        EventTimeModifyDto eventTimeModifyDto = EventTimeModifyDto.builder()
                .eventTimeId(eventTimeId)
                .title(req.getTitle())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .build();

        return eventTimeService.eventTimeAlert(eventTimeModifyDto);
    }

    @GetMapping("/eventTimeList")
    public EventTimeListVo eventTimeList(@RequestParam Long eventId) {
        return eventTimeService.eventTimeListSelect(eventId);
    }
}
