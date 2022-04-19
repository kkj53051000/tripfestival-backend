package com.tripfestival.controller;

import com.tripfestival.dto.EventCategoryProcessDto;
import com.tripfestival.request.EventCategoryProcessRequest;
import com.tripfestival.service.EventCategoryService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventCategoryController {
    private final EventCategoryService eventCategoryService;

    @PostMapping("/eventcategoryprocess")
    public ResponseVo eventCategoryProcess(
            @RequestPart MultipartFile file,
            @RequestPart("value") EventCategoryProcessRequest req) {

        EventCategoryProcessDto eventCategoryProcessDto = new EventCategoryProcessDto(file, req.getName());

        return eventCategoryService.eventCategoryInsert(eventCategoryProcessDto);
    }

    @PostMapping("/eventcategoryremove/{id}")
    public ResponseVo eventCategoryRemove(@PathVariable("id") Long eventCategoryId) {
        return eventCategoryService.eventCategoryDelete(eventCategoryId);
    }
}
