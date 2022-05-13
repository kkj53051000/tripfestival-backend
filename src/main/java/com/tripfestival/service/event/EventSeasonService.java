package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventSeason;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventSeasonImgModifyDto;
import com.tripfestival.dto.event.EventSeasonListDto;
import com.tripfestival.dto.event.EventSeasonNameModifyDto;
import com.tripfestival.exception.event.EventSeasonNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.event.EventSeasonRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.event.EventSeasonProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.event.EventListVo;
import com.tripfestival.vo.event.EventSeasonListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventSeasonService {
    private final EventSeasonRepository eventSeasonRepository;

    private final FileService fileService;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final EventRepository eventRepository;

    public ResponseVo eventSeasonInsert(MultipartFile file, EventSeasonProcessRequest req) {
        String url = fileService.s3UploadProcess(file);

        EventSeason eventSeason = EventSeason.builder()
                .name(req.getName())
                .img(url)
                .build();

        eventSeasonRepository.save(eventSeason);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventSeasonDelete(Long eventSeasonId) {
        EventSeason eventSeason = eventSeasonRepository.findById(eventSeasonId)
                .orElseThrow(() -> new EventSeasonNotFoundException());

        eventSeasonRepository.delete(eventSeason);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventSeasonNameAlert(EventSeasonNameModifyDto req) {
        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        eventSeason.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventSeasonImgAlert(EventSeasonImgModifyDto req) {
        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        String url = fileService.s3UploadProcess(req.getFile());

        eventSeason.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public EventListVo eventSeasonListSelect(EventSeasonListDto req) {
        EventSeason eventSeason = eventSeasonRepository.findById(req.getEventSeasonId())
                .orElseThrow(() -> new EventSeasonNotFoundException());

        List<Event> eventList = null;

        if (req.getWorldCountryCityRegionId() == 0) {
            WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException());

            eventList = eventRepository.findByEventSeasonAndWorldCountryCityRegion_WorldCountryCity(eventSeason, worldCountryCity);
        }else {
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            eventList = eventRepository.findByEventSeasonAndWorldCountryCityRegion(eventSeason, worldCountryCityRegion);
        }

        return new EventListVo(eventList);
    }

    public EventSeasonListVo eventSeasonAllSelect() {
        List<EventSeason> eventSeasonList = eventSeasonRepository.findAll();

        return new EventSeasonListVo(eventSeasonList);
    }
}
