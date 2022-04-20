package com.tripfestival.service;

import com.tripfestival.domain.LandmarkReview;
import com.tripfestival.exception.LandmarkReviewNotFoundException;
import com.tripfestival.repository.LandmarkReviewRepository;
import com.tripfestival.request.LandmarkReviewProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkReviewService {
    private final LandmarkReviewRepository landmarkReviewRepository;

    public ResponseVo landmarkReviewInsert(LandmarkReviewProcessRequest req) {
        LandmarkReview landmarkReview = LandmarkReview.builder()
                .content(req.getContent())
                .score(req.getScore())
                // User
                .build();

        landmarkReviewRepository.save(landmarkReview);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkReviewDelete(Long landmarkReviewId) {
        LandmarkReview landmarkReview = landmarkReviewRepository.findById(landmarkReviewId)
                .orElseThrow(() -> new LandmarkReviewNotFoundException());

        landmarkReviewRepository.delete(landmarkReview);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
