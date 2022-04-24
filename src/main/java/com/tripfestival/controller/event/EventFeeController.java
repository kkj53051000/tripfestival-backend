package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventFeeModifyDto;
import com.tripfestival.request.event.EventFeeModifyRequest;
import com.tripfestival.request.event.EventFeeProcessRequest;
import com.tripfestival.service.event.EventFeeService;
import com.tripfestival.vo.EventFeeListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventFeeController {
    private final EventFeeService eventFeeService;

    @PostMapping("/eventfeeprocess")
    public ResponseVo eventFeeProcess(@RequestBody EventFeeProcessRequest req) {
        return eventFeeService.eventFeeInsert(req);
    }

    @PostMapping("/eventfeeremove/{id}")
    public ResponseVo eventFeeRemove(@PathVariable Long eventFeeId) {
        return eventFeeService.eventFeeDelete(eventFeeId);
    }

    @PostMapping("/eventfeemodify/{id}")
    public ResponseVo eventFeeModify(@PathVariable("id") Long eventFeeId, @RequestBody EventFeeModifyRequest req) {
        EventFeeModifyDto eventFeeModifyDto = EventFeeModifyDto.builder()
                .eventFeeId(eventFeeId)
                .title(req.getTitle())
                .price(req.getPrice())
                .build();

        return eventFeeService.eventFeeAlert(eventFeeModifyDto);
    }

    @GetMapping("/eventfeelist")
    public EventFeeListVo eventFeeList(@RequestParam Long eventId) {
        return eventFeeService.eventFeeListSelect(eventId);
    }
}
