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
import com.tripfestival.vo.event.EventHashTagAllListVo;
import com.tripfestival.vo.event.EventHashTagListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventHashTagService {
    private final EventHashTagRepository eventHashTagRepository;

    private final EventRepository eventRepository;

    @Transactional
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

    @Transactional
    public ResponseVo eventHashTagDelete(Long eventHashTagId) {
        EventHashTag eventHashTag = eventHashTagRepository.findById(eventHashTagId)
                .orElseThrow(() -> new EventHashTagNotFoundException());

        eventHashTagRepository.delete(eventHashTag);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public EventHashTagListVo eventHashTagListSelect(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException());

        List<EventHashTag> eventHashTagList = eventHashTagRepository.findByEvent(event);

        return new EventHashTagListVo(eventHashTagList);
    }

    @Transactional(readOnly = true)
    public EventHashTagAllListVo eventHashTagListAllSelect() {

        List<EventHashTag> eventHashTagList = eventHashTagRepository.findAll();

        return new EventHashTagAllListVo(eventHashTagList);
    }

}
