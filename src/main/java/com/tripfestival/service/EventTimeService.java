package com.tripfestival.service;

import com.tripfestival.domain.Event;
import com.tripfestival.domain.EventTime;
import com.tripfestival.exception.EventNotFoundException;
import com.tripfestival.exception.EventTimeNotFoundException;
import com.tripfestival.repository.EventRepository;
import com.tripfestival.repository.EventTimeRepository;
import com.tripfestival.request.EventTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventTimeService {
    private final EventTimeRepository eventTimeRepository;

    private final EventRepository eventRepository;

    public ResponseVo eventTimeInsert(EventTimeProcessRequest req) {
        Event event = eventRepository.findById(req.getEventId())
                .orElseThrow(() -> new EventNotFoundException());

        EventTime eventTime = EventTime.builder()
                .title(req.getTitle())
                .time(req.getTime())
                .event(event)
                .build();

        eventTimeRepository.save(eventTime);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventTimeDelete(Long eventTimeId) {
        EventTime eventTime = eventTimeRepository.findById(eventTimeId)
                .orElseThrow(() -> new EventTimeNotFoundException());

        eventTimeRepository.delete(eventTime);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
