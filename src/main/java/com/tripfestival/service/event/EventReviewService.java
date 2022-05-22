package com.tripfestival.service.event;

import com.tripfestival.domain.event.EventReview;
import com.tripfestival.exception.event.EventReviewNotFoundException;
import com.tripfestival.repository.event.EventReviewRepository;
import com.tripfestival.request.event.EventReviewProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventReviewService {
    private final EventReviewRepository eventReviewRepository;

    @Transactional
    public ResponseVo eventReviewInsert(EventReviewProcessRequest req) {
        EventReview eventReview = EventReview.builder()
                .content(req.getContent())
                .score(req.getScore())
                // User
                .build();

        eventReviewRepository.save(eventReview);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo eventReviewDelete(Long eventReviewId) {
        EventReview eventReview = eventReviewRepository.findById(eventReviewId)
                .orElseThrow(() -> new EventReviewNotFoundException());

        eventReviewRepository.delete(eventReview);

        return new ResponseVo(Response.FAILURE, null);
    }
}
