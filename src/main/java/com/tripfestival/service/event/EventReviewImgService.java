package com.tripfestival.service.event;

import com.tripfestival.domain.event.EventReview;
import com.tripfestival.domain.event.EventReviewImg;
import com.tripfestival.dto.event.EventReviewImgProcessDto;
import com.tripfestival.exception.event.EventReviewImgNotFoundException;
import com.tripfestival.exception.event.EventReviewNotFoundException;
import com.tripfestival.repository.event.EventReviewImgRepository;
import com.tripfestival.repository.event.EventReviewRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventReviewImgService {
    private final EventReviewImgRepository eventReviewImgRepository;

    private final FileService fileService;

    private final EventReviewRepository eventReviewRepository;

    @Transactional
    public ResponseVo eventReviewImgInsert(EventReviewImgProcessDto req) {
        EventReview eventReview = eventReviewRepository.findById(req.getEventReviewId())
                .orElseThrow(() -> new EventReviewNotFoundException());

        List<String> urls  = fileService.s3UploadProcess(req.getFiles());

        for (String url : urls) {
            EventReviewImg eventReviewImg = EventReviewImg.builder()
                    .img(url)
                    .eventReview(eventReview)
                    .build();

            eventReviewImgRepository.save(eventReviewImg);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo eventReviewImgDelete(Long eventReviewImgId) {
        EventReviewImg eventReviewImg = eventReviewImgRepository.findById(eventReviewImgId)
                .orElseThrow(() -> new EventReviewImgNotFoundException());

        eventReviewImgRepository.delete(eventReviewImg);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
