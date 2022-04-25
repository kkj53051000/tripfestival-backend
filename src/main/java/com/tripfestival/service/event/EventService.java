package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventModifyDto;
import com.tripfestival.exception.event.EventCategoryNotFoundException;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.exception.event.EventSeasonNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.event.EventProcessRequest;
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

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final EventCategoryRepository eventCategoryRepository;

    private final EventSeasonRepository eventSeasonRepository;

    public ResponseVo eventInsert(EventProcessRequest req) {

        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

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
                .worldCountryCityRegion(worldCountryCityRegion)
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

    public ResponseVo eventAlert(EventModifyDto req) {
        Event event = eventRepository.findById(req.getEventId())
                .orElseThrow(() -> new EventNotFoundException());

        if(req.getName() != null) {
            event.setName(req.getName());
        }
        if(req.getDescription() != null) {
            event.setDescription(req.getDescription());
        }
        if(req.getAddress() != null) {
            event.setAddress(req.getAddress());
        }
        if(req.getVisitor() != null) {
            event.setVisitor(req.getVisitor());
        }
        if(req.getInout() != null){
            event.setInout(req.getInout());
        }
        if(req.getWorldCountryCityRegionId() != null){
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            event.setWorldCountryCityRegion(worldCountryCityRegion);
        }
        if(req.getEventCategoryId() != null) {
            EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                    .orElseThrow(() -> new EventCategoryNotFoundException());

            event.setEventCategory(eventCategory);
        }
        if(req.getEventSeasonId() != null) {
            EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                    .orElseThrow(() -> new EventSeasonNotFoundException());

            event.setEventSeason(eventSeason);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }
}
