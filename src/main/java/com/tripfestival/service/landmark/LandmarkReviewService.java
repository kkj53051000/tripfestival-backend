package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkReview;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.landmark.LandmarkReviewNotFoundException;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.landmark.LandmarkReviewRepository;
import com.tripfestival.request.landmark.LandmarkReviewProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkReviewListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkReviewService {
    private final LandmarkReviewRepository landmarkReviewRepository;

    private final LandmarkRepository landmarkRepository;

    @Transactional
    public ResponseVo landmarkReviewInsert(LandmarkReviewProcessRequest req) {

        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException());

        LandmarkReview landmarkReview = LandmarkReview.builder()
                .content(req.getContent())
                .score(req.getScore())
                // User
                .landmark(landmark)
                .build();

        landmarkReviewRepository.save(landmarkReview);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo landmarkReviewDelete(Long landmarkReviewId) {
        LandmarkReview landmarkReview = landmarkReviewRepository.findById(landmarkReviewId)
                .orElseThrow(() -> new LandmarkReviewNotFoundException());

        landmarkReviewRepository.delete(landmarkReview);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public LandmarkReviewListVo landmarkReviewListSelect(Long landmarkId) {
        Landmark landmark = landmarkRepository.findById(landmarkId)
                .orElseThrow(() -> new LandmarkNotFoundException());

        List<LandmarkReview> landmarkReviewList = landmarkReviewRepository.findByLandmark(landmark);

        return new LandmarkReviewListVo(landmarkReviewList);
    }
}
