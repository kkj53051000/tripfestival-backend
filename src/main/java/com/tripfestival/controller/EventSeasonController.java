package com.tripfestival.controller;

import com.tripfestival.dto.EventSeasonImgModifyDto;
import com.tripfestival.dto.EventSeasonNameModifyDto;
import com.tripfestival.request.EventSeasonNameModifyRequest;
import com.tripfestival.request.EventSeasonProcessRequest;
import com.tripfestival.service.EventSeasonService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventSeasonController {
    private final EventSeasonService eventSeasonService;

    @PostMapping("/eventseasonprocess")
    public ResponseVo eventSeasonProcess(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "value", required = false)EventSeasonProcessRequest req) {

        return eventSeasonService.eventSeasonInsert(file, req);
    }

    @PostMapping("/eventseasonremove/{id}")
    public ResponseVo eventSeasonRemove(@PathVariable("id") Long eventSeasonId) {
        return eventSeasonService.eventSeasonDelete(eventSeasonId);
    }

    @PostMapping("/eventseasonnamemodify/{id}")
    public ResponseVo eventSeasonNameModify(
            @PathVariable("id") Long eventSeasonId,
            @RequestBody EventSeasonNameModifyRequest req) {

        EventSeasonNameModifyDto eventSeasonNameModifyDto = EventSeasonNameModifyDto.builder()
                .eventSeasonId(eventSeasonId)
                .name(req.getName())
                .build();

        return eventSeasonService.eventSeasonNameAlert(eventSeasonNameModifyDto);
    }

    @PostMapping("/eventseasonimgmodify/{id}")
    public ResponseVo eventSeasonImgModify(
            @PathVariable("id") Long eventSeasonId,
            @RequestPart MultipartFile file) {

        EventSeasonImgModifyDto eventSeasonImgModifyDto = EventSeasonImgModifyDto.builder()
                .eventSeasonId(eventSeasonId)
                .file(file)
                .build();

        return eventSeasonService.eventSeasonImgAlert(eventSeasonImgModifyDto);
    }
}
