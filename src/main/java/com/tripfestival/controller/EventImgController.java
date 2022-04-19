package com.tripfestival.controller;

import com.tripfestival.dto.EventImgProcessDto;
import com.tripfestival.request.EventImgProcessRequest;
import com.tripfestival.service.EventImgService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventImgController {
    private final EventImgService eventImgService;

    @PostMapping("/eventimgprocess")
    public ResponseVo eventImgProcess(
            @RequestPart List<MultipartFile> files,
            @RequestPart(name = "value")EventImgProcessRequest req) {

        EventImgProcessDto eventImgProcessDto = new EventImgProcessDto(files, req.getEventId());

        return eventImgService.eventImgInsert(eventImgProcessDto);
    }

    @PostMapping("/eventimgremove/{id}")
    public ResponseVo eventImgRemove(@PathVariable("id") Long eventImgId) {
        return eventImgService.eventImgDelete(eventImgId);
    }
}
