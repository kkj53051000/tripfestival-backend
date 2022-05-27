package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventCategoryImgModifyDto;
import com.tripfestival.dto.event.EventCategoryListDto;
import com.tripfestival.dto.event.EventCategoryNameModifyDto;
import com.tripfestival.dto.event.EventCategoryProcessDto;
import com.tripfestival.request.event.EventCategoryModifyRequest;
import com.tripfestival.request.event.EventCategoryProcessRequest;
import com.tripfestival.service.event.EventCategoryService;
import com.tripfestival.vo.event.EventCategoryAllListVo;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventCategoryListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventCategoryController {  // 축제 종류
    private final EventCategoryService eventCategoryService;

    @PostMapping("/admin/eventCategoryProcess")
    public ResponseVo eventCategoryProcess(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "value") EventCategoryProcessRequest req) {

        EventCategoryProcessDto eventCategoryProcessDto = new EventCategoryProcessDto(file, req.getName());

        return eventCategoryService.eventCategoryInsert(eventCategoryProcessDto);
    }

    @PostMapping("/admin/eventCategoryRemove/{id}")
    public ResponseVo eventCategoryRemove(@PathVariable("id") Long eventCategoryId) {
        return eventCategoryService.eventCategoryDelete(eventCategoryId);
    }

    @PostMapping("/admin/eventCategoryNameModify/{id}")
    public ResponseVo eventCategoryNameModify(
            @PathVariable("id") Long eventCategoryId,
            @RequestBody EventCategoryModifyRequest req) {

        EventCategoryNameModifyDto eventCategoryNameModifyDto
                = new EventCategoryNameModifyDto(eventCategoryId, req);

        return eventCategoryService.eventCategoryNameModify(eventCategoryNameModifyDto);
    }

    @PostMapping("/admin/eventCategoryImgModify/{id}")
    public ResponseVo eventCategoryImgModify(
            @PathVariable("id") Long eventCategoryId,
            @RequestPart MultipartFile file) {

        EventCategoryImgModifyDto eventCategoryImgModifyDto
                = new EventCategoryImgModifyDto(eventCategoryId, file);

        return eventCategoryService.eventCategoryImgModify(eventCategoryImgModifyDto);
    }

    @GetMapping("/eventCategoryList")
    public EventCategoryListVo eventCategoryList(@RequestParam Long eventCategoryId,
                                                 @RequestParam Long worldCountryCityId,
                                                 @RequestParam Long worldCountryCityRegionId) {

        EventCategoryListDto eventCategoryListDto = EventCategoryListDto.builder()
                .eventCategoryId(eventCategoryId)
                .worldCountryCityId(worldCountryCityId)
                .worldCountryCityRegionId(worldCountryCityRegionId)
                .build();

        return eventCategoryService.eventCategoryListSelect(eventCategoryListDto);
    }

    @GetMapping("/eventCategoryAllList")
    public EventCategoryAllListVo eventCategoryAllList() {
        return eventCategoryService.eventCategoryAllSelect();
    }
}
