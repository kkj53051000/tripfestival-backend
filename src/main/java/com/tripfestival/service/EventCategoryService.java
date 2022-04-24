package com.tripfestival.service;

import com.tripfestival.domain.EventCategory;
import com.tripfestival.dto.EventCategoryImgModifyDto;
import com.tripfestival.dto.EventCategoryNameModifyDto;
import com.tripfestival.dto.EventCategoryProcessDto;
import com.tripfestival.exception.EventCategoryNotFoundException;
import com.tripfestival.repository.EventCategoryRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
}
