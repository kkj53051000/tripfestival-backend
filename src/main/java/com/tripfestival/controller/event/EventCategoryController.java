package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventCategoryImgModifyDto;
import com.tripfestival.dto.event.EventCategoryNameModifyDto;
import com.tripfestival.dto.event.EventCategoryProcessDto;
import com.tripfestival.request.event.EventCategoryModifyRequest;
import com.tripfestival.request.event.EventCategoryProcessRequest;
import com.tripfestival.service.event.EventCategoryService;
import com.tripfestival.vo.EventCategoryAllListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventCategoryController {  // 축제 종류
    private final EventCategoryService eventCategoryService;

    @PostMapping("/eventCategoryProcess")
    public ResponseVo eventCategoryProcess(
            @RequestPart MultipartFile file,
            @RequestPart("value") EventCategoryProcessRequest req) {

        EventCategoryProcessDto eventCategoryProcessDto = new EventCategoryProcessDto(file, req.getName());

        return eventCategoryService.eventCategoryInsert(eventCategoryProcessDto);
    }

    @PostMapping("/eventCategoryRemove/{id}")
    public ResponseVo eventCategoryRemove(@PathVariable("id") Long eventCategoryId) {
        return eventCategoryService.eventCategoryDelete(eventCategoryId);
    }

    @PostMapping("/eventCategoryNameModify/{id}")
    public ResponseVo eventCategoryNameModify(
            @PathVariable("id") Long eventCategoryId,
            @RequestBody EventCategoryModifyRequest req) {

        EventCategoryNameModifyDto eventCategoryNameModifyDto
                = new EventCategoryNameModifyDto(eventCategoryId, req);

        return eventCategoryService.eventCategoryNameModify(eventCategoryNameModifyDto);
    }

    @PostMapping("/eventCategoryImgModify/{id}")
    public ResponseVo eventCategoryImgModify(
            @PathVariable("id") Long eventCategoryId,
            @RequestPart MultipartFile file) {

        EventCategoryImgModifyDto eventCategoryImgModifyDto
                = new EventCategoryImgModifyDto(eventCategoryId, file);

        return eventCategoryService.eventCategoryImgModify(eventCategoryImgModifyDto);
    }

    @PostMapping("/eventCategoryAllList")
    public EventCategoryAllListVo eventCategoryAllList() {
        return eventCategoryService.eventCategoryAllSelect();
    }
}
