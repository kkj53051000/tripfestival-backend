package com.tripfestival.service;

import com.tripfestival.domain.EventReview;
import com.tripfestival.exception.EventReviewNotFoundException;
import com.tripfestival.repository.EventReviewImgRepository;
import com.tripfestival.repository.EventReviewRepository;
import com.tripfestival.request.EventReviewProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventReviewService {
    private final EventReviewRepository eventReviewRepository;

    public ResponseVo eventReviewInsert(EventReviewProcessRequest req) {
        EventReview eventReview = EventReview.builder()
                .content(req.getContent())
                .score(req.getScore())
                // User
                .build();

        eventReviewRepository.save(eventReview);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo eventReviewDelete(Long eventReviewId) {
        EventReview eventReview = eventReviewRepository.findById(eventReviewId)
                .orElseThrow(() -> new EventReviewNotFoundException());

        eventReviewRepository.delete(eventReview);

        return new ResponseVo(Response.FAILURE, null);
    }
}
