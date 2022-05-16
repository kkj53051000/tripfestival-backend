package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.event.EventHashTag;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventListDto;
import com.tripfestival.dto.event.EventModifyDto;
import com.tripfestival.dto.event.EventProcessDto;
import com.tripfestival.exception.event.EventCategoryNotFoundException;
import com.tripfestival.exception.event.EventNotFoundException;
import com.tripfestival.exception.event.EventSeasonNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventHashTagRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.event.EventProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventAllListVo;
import com.tripfestival.vo.event.EventListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final EventCategoryRepository eventCategoryRepository;

    private final EventSeasonRepository eventSeasonRepository;

    private final FileService fileService;

    private final EventHashTagRepository eventHashTagRepository;

    public ResponseVo eventInsert(EventProcessDto req) {

        String url = fileService.s3UploadProcess(req.getFile());

        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        Event event = Event.builder()
                .name(req.getName())
                .img(url)
                .description(req.getDescription())
                .address(req.getAddress())
                .visitor(req.getVisitor())
                .inout((req.getInout() != 0))
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

    public EventListVo eventListSelect(EventListDto req) {

        List<Event> eventList = null;

        if (req.getWorldCountryCityRegionId() == 0) {  // city 선택 region 전체
            WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException());

            eventList = eventRepository.findByWorldCountryCityRegion_WorldCountryCity(worldCountryCity);
        }else {  // city 선택 region 선택
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            eventList = eventRepository.findByWorldCountryCityRegion(worldCountryCityRegion);
        }

        // EventHashTag List
        List<List<EventHashTag>> eventHashTagListList = new ArrayList<>();

        for (Event event : eventList) {
            List<EventHashTag> eventHashTagList = eventHashTagRepository.findByEvent(event);

            if(eventHashTagList.size() == 0) {
                eventHashTagListList.add(new ArrayList<EventHashTag>());
            }else {
                eventHashTagListList.add(eventHashTagList);
            }
        }

        return new EventListVo(eventList, eventHashTagListList);
    }

    public EventAllListVo eventAllListSelect() {
        List<Event> eventList = eventRepository.findAll();

        return new EventAllListVo(eventList);
    }
}
