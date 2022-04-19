package com.tripfestival.controller;

import com.tripfestival.request.EventFeeProcessRequest;
import com.tripfestival.service.EventFeeService;
import com.tripfestival.vo.Response;
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

    @PostMapping("/eventfeeremive/{id}")
    public ResponseVo eventFeeRemove(@PathVariable Long eventFeeId) {
        return eventFeeService.eventFeeDelete(eventFeeId);
    }
}
