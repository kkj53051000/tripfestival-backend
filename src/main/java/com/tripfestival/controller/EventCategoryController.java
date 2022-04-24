package com.tripfestival.controller;

import com.tripfestival.dto.EventCategoryImgModifyDto;
import com.tripfestival.dto.EventCategoryNameModifyDto;
import com.tripfestival.dto.EventCategoryProcessDto;
import com.tripfestival.request.EventCategoryModifyRequest;
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

    @PostMapping("/eventcategorynamemodify/{id}")
    public ResponseVo eventCategoryNameModify(
            @PathVariable("id") Long eventCategoryId,
            @RequestBody EventCategoryModifyRequest req) {
        EventCategoryNameModifyDto eventCategoryNameModifyDto = EventCategoryNameModifyDto.builder()
                .eventCategoryId(eventCategoryId)
                .name(req.getName())
                .build();

        return eventCategoryService.eventCategoryNameModify(eventCategoryNameModifyDto);
    }

    @PostMapping("/eventcategoryimgmodify/{id}")
    public ResponseVo eventCategoryImgModify(
            @PathVariable("id") Long eventCategoryId,
            @RequestPart MultipartFile file) {
        EventCategoryImgModifyDto eventCategoryImgModifyDto = EventCategoryImgModifyDto.builder()
                .file(file)
                .eventCategoryId(eventCategoryId)
                .build();

        return eventCategoryService.eventCategoryImgModify(eventCategoryImgModifyDto);
    }
}
