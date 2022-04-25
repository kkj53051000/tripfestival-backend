package com.tripfestival.service.event;

import com.tripfestival.domain.event.EventCategory;
import com.tripfestival.dto.event.EventCategoryImgModifyDto;
import com.tripfestival.dto.event.EventCategoryNameModifyDto;
import com.tripfestival.dto.event.EventCategoryProcessDto;
import com.tripfestival.exception.event.EventCategoryNotFoundException;
import com.tripfestival.repository.event.EventCategoryRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.event.EventCategoryAllListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventCategoryService {
    private final EventCategoryRepository eventCategoryRepository;

    private final FileService fileService;

    public ResponseVo eventCategoryInsert(EventCategoryProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        EventCategory eventCategory = EventCategory.builder()
                .name(req.getName())
                .img(url)
                .build();

        eventCategoryRepository.save(eventCategory);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventCategoryDelete(Long eventCategoryId) {
        EventCategory eventCategory = eventCategoryRepository.findById(eventCategoryId)
                        .orElseThrow(() -> new EventCategoryNotFoundException());

        eventCategoryRepository.delete(eventCategory);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventCategoryNameModify(EventCategoryNameModifyDto req) {
        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        eventCategory.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventCategoryImgModify(EventCategoryImgModifyDto req) {
        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new EventCategoryNotFoundException());

        String url = fileService.s3UploadProcess(req.getFile());

        eventCategory.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public EventCategoryAllListVo eventCategoryAllSelect() {
        List<EventCategory> eventCategoryList = eventCategoryRepository.findAll();

        return new EventCategoryAllListVo(eventCategoryList);
    }
}
