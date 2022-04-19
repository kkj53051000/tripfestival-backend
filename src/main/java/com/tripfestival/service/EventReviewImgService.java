package com.tripfestival.service;

import com.tripfestival.domain.EventReview;
import com.tripfestival.domain.EventReviewImg;
import com.tripfestival.dto.EventReviewImgProcessDto;
import com.tripfestival.exception.EventReviewImgNotFoundException;
import com.tripfestival.exception.EventReviewNotFoundException;
import com.tripfestival.repository.EventReviewImgRepository;
import com.tripfestival.repository.EventReviewRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventReviewImgService {
    private final EventReviewImgRepository eventReviewImgRepository;

    private final FileService fileService;

    private final EventReviewRepository eventReviewRepository;

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

    public ResponseVo eventReviewImgDelete(Long eventReviewImgId) {
        EventReviewImg eventReviewImg = eventReviewImgRepository.findById(eventReviewImgId)
                .orElseThrow(() -> new EventReviewImgNotFoundException());

        eventReviewImgRepository.delete(eventReviewImg);

        return new ResponseVo(Response.FAILURE, null);
    }
}
