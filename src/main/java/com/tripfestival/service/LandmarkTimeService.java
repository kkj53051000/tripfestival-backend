package com.tripfestival.service;

import com.tripfestival.domain.LandmarkTime;
import com.tripfestival.dto.LandmarkTimeModifyDto;
import com.tripfestival.exception.LandmarkTimeNotFoundException;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.LandmarkTimeRepository;
import com.tripfestival.request.LandmarkTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkTimeService {
    private final LandmarkTimeRepository landmarkTimeRepository;

    private final LandmarkRepository landmarkRepository;

    public ResponseVo landmarkTimeInsert(LandmarkTimeProcessRequest req) {
        LandmarkTime landmarkTime = LandmarkTime.builder()
                .title(req.getTitle())
                .price(req.getPrice())
                .landmark(landmarkRepository.findById(req.getLandmarkId()).orElseThrow(() -> new LandmarkTimeNotFoundException()))
                .build();

        landmarkTimeRepository.save(landmarkTime);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkTimeDelete(Long landmarkTimeId) {
        LandmarkTime landmarkTime = landmarkTimeRepository.findById(landmarkTimeId)
                .orElseThrow(() -> new LandmarkTimeNotFoundException());

        landmarkTimeRepository.delete(landmarkTime);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkTimeAlert(LandmarkTimeModifyDto req) {
        LandmarkTime landmarkTime = landmarkTimeRepository.findById(req.getLandmarkTimeId())
                .orElseThrow(() -> new LandmarkTimeNotFoundException());

        if(req.getTitle() != null) {
            landmarkTime.setTitle(req.getTitle());
        }
        if(req.getPrice() != null) {
            landmarkTime.setPrice(req.getPrice());
        }

        return new ResponseVo(Response.SUCCESS, null);
    }
}
