package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventImg;
import com.tripfestival.dto.event.EventImgProcessDto;
import com.tripfestival.exception.event.EventImgNotFoundException;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.repository.event.EventImgRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.service.file.FileService;
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

    public ResponseVo eventImgInsert(EventImgProcessDto req) {

        Event event = eventRepository.findById(req.getEventId())
                .orElseThrow(() -> new EventNotFoundException());

        List<String> urls = fileService.s3UploadProcess(req.getFiles());

        for (String url : urls) {
            EventImg eventImg = EventImg.builder()
                    .img(url)
                    .event(event)
                    .build();

            eventImgRepository.save(eventImg);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventImgDelete(Long eventImgId) {
        EventImg eventImg = eventImgRepository.findById(eventImgId)
                        .orElseThrow(() -> new EventImgNotFoundException());

        eventImgRepository.delete(eventImg);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
