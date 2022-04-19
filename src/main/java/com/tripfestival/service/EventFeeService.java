package com.tripfestival.service;

import com.tripfestival.domain.Event;
import com.tripfestival.domain.EventFee;
import com.tripfestival.exception.EventFeeNotFoundException;
import com.tripfestival.exception.EventNotFoundException;
import com.tripfestival.repository.EventFeeRepository;
import com.tripfestival.repository.EventRepository;
import com.tripfestival.request.EventFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventFeeService {
    private final EventFeeRepository eventFeeRepository;

    private final EventRepository eventRepository;

    public ResponseVo eventFeeInsert(EventFeeProcessRequest req) {

        Event event = eventRepository.findById(req.getEventId())
                .orElseThrow(() -> new EventNotFoundException());

        EventFee eventFee = EventFee.builder()
                .title(req.getTitle())
                .price(req.getPrice())
                .event(event)
                .build();

        eventFeeRepository.save(eventFee);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventFeeDelete(Long eventFeeId) {
        EventFee eventFee = eventFeeRepository.findById(eventFeeId)
                .orElseThrow(() -> new EventFeeNotFoundException());

        eventFeeRepository.delete(eventFee);

        return new ResponseVo(Response.SUCCESS, null);
    }

}
