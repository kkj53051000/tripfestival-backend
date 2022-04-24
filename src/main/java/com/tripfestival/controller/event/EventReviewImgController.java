package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventReviewImgProcessDto;
import com.tripfestival.request.event.EventReviewImgProcessRequest;
import com.tripfestival.service.event.EventReviewImgService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventReviewImgController {
    private final EventReviewImgService eventReviewImgService;

    @PostMapping("/eventReviewImgProcess")
    public ResponseVo eventReviewImgProcess(
            @RequestPart List<MultipartFile> files,
            @RequestPart(name = "value") EventReviewImgProcessRequest req) {

        EventReviewImgProcessDto eventReviewImgProcessDto = new EventReviewImgProcessDto(files, req.getEventReviewId());

        return eventReviewImgService.eventReviewImgInsert(eventReviewImgProcessDto);
    }

    @PostMapping("/eventReviewImgRemove/{id}")
    public ResponseVo eventReviewImgRemove(@PathVariable("id") Long eventReviewImgId) {
        return eventReviewImgService.eventReviewImgDelete(eventReviewImgId);
    }
}
