package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventImgProcessDto;
import com.tripfestival.request.event.EventImgProcessRequest;
import com.tripfestival.service.event.EventImgService;
import com.tripfestival.vo.EventImgListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventImgController {  // 축제 이미지
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

    @GetMapping("/eventimglist")
    public EventImgListVo eventImgList(@RequestParam Long eventId) {
        return eventImgService.eventImgList(eventId);
    }
}
