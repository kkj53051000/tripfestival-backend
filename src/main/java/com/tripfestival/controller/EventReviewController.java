package com.tripfestival.controller;

import com.tripfestival.request.EventReviewProcessRequest;
import com.tripfestival.service.EventReviewService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventReviewController {
    private final EventReviewService eventReviewService;

    @PostMapping("/eventreviewprocess")
    public ResponseVo eventReviewProcess(@RequestBody EventReviewProcessRequest req) {
        return eventReviewService.eventReviewInsert(req);
    }

    @PostMapping("/eventreviewremove/{id}")
    public ResponseVo eventReviewRemove(@PathVariable("id") Long eventReviewId) {
        return eventReviewService.eventReviewDelete(eventReviewId);
    }
}
