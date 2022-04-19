package com.tripfestival.service;

import com.tripfestival.domain.Event;
import com.tripfestival.domain.EventImg;
import com.tripfestival.dto.EventImgProcessDto;
import com.tripfestival.exception.EventImgNotFoundException;
import com.tripfestival.exception.EventNotFoundException;
import com.tripfestival.repository.EventImgRepository;
import com.tripfestival.repository.EventRepository;
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
