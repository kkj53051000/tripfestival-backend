package com.tripfestival.controller.event;

import com.tripfestival.request.event.EventReviewProcessRequest;
import com.tripfestival.service.event.EventReviewService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventReviewController {
    private final EventReviewService eventReviewService;

    @PostMapping("/eventReviewProcess")
    public ResponseVo eventReviewProcess(@RequestBody EventReviewProcessRequest req) {
        return eventReviewService.eventReviewInsert(req);
    }

    @PostMapping("/eventReviewRemove/{id}")
    public ResponseVo eventReviewRemove(@PathVariable("id") Long eventReviewId) {
        return eventReviewService.eventReviewDelete(eventReviewId);
    }
}
