package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventImgProcessDto;
import com.tripfestival.request.event.EventImgProcessRequest;
import com.tripfestival.service.event.EventImgService;
import com.tripfestival.vo.event.EventImgListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventImgController {  // 축제 이미지
    private final EventImgService eventImgService;

    @PostMapping("/admin/eventImgProcess")
    public ResponseVo eventImgProcess(
            @RequestPart(name = "files", required = false) List<MultipartFile> files,
            @RequestPart(name = "value") EventImgProcessRequest req) {

        System.out.println(req.getEventId());
        System.out.println(files.size());

        EventImgProcessDto eventImgProcessDto = new EventImgProcessDto(files, req.getEventId());

        return eventImgService.eventImgInsert(eventImgProcessDto);
    }

    @PostMapping("/admin/eventImgRemove/{id}")
    public ResponseVo eventImgRemove(@PathVariable("id") Long eventImgId) {
        return eventImgService.eventImgDelete(eventImgId);
    }

    @GetMapping("/eventImgList")
    public EventImgListVo eventImgList(@RequestParam Long eventId) {
        return eventImgService.eventImgList(eventId);
    }
}
