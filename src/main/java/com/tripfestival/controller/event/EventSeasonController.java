package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventSeasonImgModifyDto;
import com.tripfestival.dto.event.EventSeasonNameModifyDto;
import com.tripfestival.request.event.EventSeasonNameModifyRequest;
import com.tripfestival.request.event.EventSeasonProcessRequest;
import com.tripfestival.service.event.EventSeasonService;
import com.tripfestival.vo.event.EventSeasonListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventSeasonController {
    private final EventSeasonService eventSeasonService;

    @PostMapping("/eventSeasonProcess")
    public ResponseVo eventSeasonProcess(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "value", required = false)EventSeasonProcessRequest req) {

        return eventSeasonService.eventSeasonInsert(file, req);
    }

    @PostMapping("/eventSeasonRemove/{id}")
    public ResponseVo eventSeasonRemove(@PathVariable("id") Long eventSeasonId) {
        return eventSeasonService.eventSeasonDelete(eventSeasonId);
    }

    @PostMapping("/eventSeasonNameModify/{id}")
    public ResponseVo eventSeasonNameModify(
            @PathVariable("id") Long eventSeasonId,
            @RequestBody EventSeasonNameModifyRequest req) {

        EventSeasonNameModifyDto eventSeasonNameModifyDto = EventSeasonNameModifyDto.builder()
                .eventSeasonId(eventSeasonId)
                .name(req.getName())
                .build();

        return eventSeasonService.eventSeasonNameAlert(eventSeasonNameModifyDto);
    }

    @PostMapping("/eventSeasonImgModify/{id}")
    public ResponseVo eventSeasonImgModify(
            @PathVariable("id") Long eventSeasonId,
            @RequestPart MultipartFile file) {

        EventSeasonImgModifyDto eventSeasonImgModifyDto = EventSeasonImgModifyDto.builder()
                .eventSeasonId(eventSeasonId)
                .file(file)
                .build();

        return eventSeasonService.eventSeasonImgAlert(eventSeasonImgModifyDto);
    }

    @PostMapping("/eventSeasonAllList")
    public EventSeasonListVo eventSeasonAllList() {
        return eventSeasonService.eventSeasonAllSelect();
    }
}
