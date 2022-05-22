package com.tripfestival.service.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.event.EventCategoryImgModifyDto;
import com.tripfestival.dto.event.EventCategoryListDto;
import com.tripfestival.dto.event.EventCategoryNameModifyDto;
import com.tripfestival.dto.event.EventCategoryProcessDto;
import com.tripfestival.exception.event.EventCategoryNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.repository.event.EventRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.event.EventCategoryAllListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.event.EventCategoryListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCategoryService {
    private final EventCategoryRepository eventCategoryRepository;

    private final EventRepository eventRepository;

    private final FileService fileService;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    @Transactional
    public ResponseVo eventCategoryInsert(EventCategoryProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        EventCategory eventCategory = EventCategory.builder()
                .name(req.getName())
                .img(url)
                .build();

        eventCategoryRepository.save(eventCategory);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo eventCategoryDelete(Long eventCategoryId) {
        EventCategory eventCategory = eventCategoryRepository.findById(eventCategoryId)
                        .orElseThrow(() -> new EventCategoryNotFoundException());

        eventCategoryRepository.delete(eventCategory);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo eventCategoryNameModify(EventCategoryNameModifyDto req) {
        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        eventCategory.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo eventCategoryImgModify(EventCategoryImgModifyDto req) {
        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        String url = fileService.s3UploadProcess(req.getFile());

        eventCategory.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public EventCategoryListVo eventCategoryListSelect(EventCategoryListDto req) {
        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        List<Event> eventList = null;

        if (req.getWorldCountryCityRegionId() == 0) {
            WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException());

            eventList = eventRepository.findByEventCategoryAndWorldCountryCityRegion_WorldCountryCity(eventCategory, worldCountryCity);
        }else {
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            eventList = eventRepository.findByEventCategoryAndWorldCountryCityRegion(eventCategory, worldCountryCityRegion);
        }

        return new EventCategoryListVo(eventList);
    }

    @Transactional(readOnly = true)
    public EventCategoryAllListVo eventCategoryAllSelect() {
        List<EventCategory> eventCategoryList = eventCategoryRepository.findAll();

        return new EventCategoryAllListVo(eventCategoryList);
    }
}
