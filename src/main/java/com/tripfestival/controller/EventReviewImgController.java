package com.tripfestival.controller;

import com.tripfestival.dto.EventReviewImgProcessDto;
import com.tripfestival.request.EventReviewImgProcessRequest;
import com.tripfestival.service.EventReviewImgService;
import com.tripfestival.service.EventReviewService;
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

    @PostMapping("/eventreviewimgprocess")
    public ResponseVo eventReviewImgProcess(
            @RequestPart List<MultipartFile> files,
            @RequestPart(name = "value") EventReviewImgProcessRequest req) {

        EventReviewImgProcessDto eventReviewImgProcessDto = new EventReviewImgProcessDto(files, req.getEventReviewId());

        return eventReviewImgService.eventReviewImgInsert(eventReviewImgProcessDto);
    }

    @PostMapping("/eventreviewimgremove/{id}")
    public ResponseVo eventReviewImgRemove(@PathVariable("id") Long eventReviewImgId) {
        return eventReviewImgService.eventReviewImgDelete(eventReviewImgId);
    }
}
