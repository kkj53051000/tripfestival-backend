package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventTime;
import com.tripfestival.dto.event.EventTimeModifyDto;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.exception.event.EventTimeNotFoundException;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventTimeRepository;
import com.tripfestival.request.event.EventTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

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
                .startTime(LocalTime.parse(req.getStartTime()))
                .endTime(LocalTime.parse(req.getEndTime()))
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

    public ResponseVo eventTimeAlert(EventTimeModifyDto req) {
        EventTime eventTime = eventTimeRepository.findById(req.getEventTimeId())
                .orElseThrow(() -> new EventTimeNotFoundException());

        if(req.getTitle() != null) {
            eventTime.setTitle(req.getTitle());
        }
        if(req.getStartTime() != null) {
            eventTime.setStartTime(LocalTime.parse(req.getStartTime()));
        }
        if(req.getEndTime() != null) {
            eventTime.setEndTime(LocalTime.parse(req.getEndTime()));
        }

        return new ResponseVo(Response.SUCCESS, null);
    }
}
