package com.tripfestival.service;

import com.tripfestival.domain.Event;
import com.tripfestival.domain.EventCategory;
import com.tripfestival.domain.EventSeason;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.exception.EventCategoryNotFoundException;
import com.tripfestival.exception.EventNotFoundException;
import com.tripfestival.exception.EventSeasonNotFoundException;
import com.tripfestival.exception.WorldCountryCityNotFoundException;
import com.tripfestival.repository.EventCategoryRepository;
import com.tripfestival.repository.EventRepository;
import com.tripfestival.repository.EventSeasonRepository;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.request.EventProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final EventCategoryRepository eventCategoryRepository;

    private final EventSeasonRepository eventSeasonRepository;

    public ResponseVo eventInsert(EventProcessRequest req) {

        WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                .orElseThrow(() -> new WorldCountryCityNotFoundException());

        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        Event event = Event.builder()
                .name(req.getName())
                .description(req.getDescription())
                .address(req.getAddress())
                .visitor(req.getVisitor())
                .inout(req.isInout())
                .worldCountryCity(worldCountryCity)
                .eventCategory(eventCategory)
                .eventSeason(eventSeason)
                .build();

        eventRepository.save(event);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventDelete(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException());

        eventRepository.delete(event);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
