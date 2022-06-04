package com.tripfestival.controller.event;

import com.tripfestival.dto.event.EventListDto;
import com.tripfestival.dto.event.EventModifyDto;
import com.tripfestival.dto.event.EventProcessDto;
import com.tripfestival.request.event.EventModifyRequest;
import com.tripfestival.request.event.EventProcessRequest;
import com.tripfestival.service.event.EventService;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventAllListVo;
import com.tripfestival.vo.event.EventListVo;
import com.tripfestival.vo.event.EventVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    @PostMapping("/admin/eventProcess")
    public ResponseVo eventProcess(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "value") EventProcessRequest req) {

        EventProcessDto eventProcessDto = new EventProcessDto(file, req);

        return eventService.eventInsert(eventProcessDto);
    }

    @PostMapping("/admin/eventRemove/{id}")
    public ResponseVo eventRemove(@PathVariable("id") Long eventId) {
        return eventService.eventDelete(eventId);
    }

    @PostMapping("/admin/eventModify/{id}")
    public ResponseVo eventModify(
            @PathVariable("id") Long eventId,
            @RequestBody EventModifyRequest req) {

        EventModifyDto eventModifyDto = new EventModifyDto(eventId, req);

        return eventService.eventAlert(eventModifyDto);
    }

    @GetMapping("/eventList")
    public EventListVo eventList(
            @RequestParam Long worldCountryCityId,
            @RequestParam Long worldCountryCityRegionId) {
        EventListDto eventListDto = EventListDto.builder()
                .worldCountryCityId(worldCountryCityId)
                .worldCountryCityRegionId(worldCountryCityRegionId)
                .build();

        return eventService.eventListSelect(eventListDto);
    }

    @GetMapping("/eventAllList")
    public EventAllListVo eventAllList() {
        return eventService.eventAllListSelect();
    }

    @GetMapping("/event/{id}")
    public EventVo event(@PathVariable("id") Long eventId) {
        return eventService.eventSelect(eventId);
    }

}