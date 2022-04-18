package com.tripfestival.controller;

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
}
