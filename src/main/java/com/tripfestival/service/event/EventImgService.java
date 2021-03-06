package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventImg;
import com.tripfestival.dto.event.EventImgProcessDto;
import com.tripfestival.exception.event.EventImgNotFoundException;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.repository.event.EventImgRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.event.EventImgListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventImgService {
    private final EventImgRepository eventImgRepository;

    private final FileService fileService;

    private final EventRepository eventRepository;

    @Transactional
    public ResponseVo eventImgInsert(EventImgProcessDto req) {

        Event event = eventRepository.findById(req.getEventId())
                .orElseThrow(() -> new EventNotFoundException());

        List<String> urlList = fileService.s3UploadProcess(req.getFiles());

        for (String url : urlList) {
            EventImg eventImg = EventImg.builder()
                    .img(url)
                    .event(event)
                    .build();

            eventImgRepository.save(eventImg);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo eventImgDelete(Long eventImgId) {
        EventImg eventImg = eventImgRepository.findById(eventImgId)
                        .orElseThrow(() -> new EventImgNotFoundException());

        eventImgRepository.delete(eventImg);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public EventImgListVo eventImgList(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException());

        List<EventImg> eventImgList = eventImgRepository.findByEvent(event)
                .orElseThrow(() -> new EventImgNotFoundException());

        return new EventImgListVo(eventImgList);
    }
}
