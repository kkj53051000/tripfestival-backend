package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventFee;
import com.tripfestival.dto.event.EventFeeModifyDto;
import com.tripfestival.exception.event.EventFeeNotFoundException;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.repository.event.EventFeeRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.request.event.EventFeeProcessRequest;
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

    public ResponseVo eventFeeAlert(EventFeeModifyDto req) {
        EventFee eventFee = eventFeeRepository.findById(req.getEventFeeId())
                .orElseThrow(() -> new EventFeeNotFoundException());

        if(req.getTitle() != null) {
            eventFee.setTitle(req.getTitle());
        }
        if(req.getPrice() != null) {
            eventFee.setPrice(req.getPrice());
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

}
