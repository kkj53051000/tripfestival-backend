package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventHashTag;
import com.tripfestival.exception.event.EventHashTagNotFoundException;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.repository.event.EventHashTagRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.request.event.EventHashTagProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventHashTagService {
    private final EventHashTagRepository eventHashTagRepository;

    private final EventRepository eventRepository;

    public ResponseVo eventHashTagInsert(EventHashTagProcessRequest req) {
        Event event = eventRepository.findById(req.getEventId())
                .orElseThrow(() -> new EventNotFoundException());

        EventHashTag eventHashTag = EventHashTag.builder()
                .name(req.getName())
                .event(event)
                .build();

        eventHashTagRepository.save(eventHashTag);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventHashTagDelete(Long eventHashTagId) {
        EventHashTag eventHashTag = eventHashTagRepository.findById(eventHashTagId)
                .orElseThrow(() -> new EventHashTagNotFoundException());

        eventHashTagRepository.delete(eventHashTag);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
