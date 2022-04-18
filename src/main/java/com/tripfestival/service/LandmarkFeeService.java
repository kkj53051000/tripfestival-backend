package com.tripfestival.service;

import com.tripfestival.domain.LandmarkFee;
import com.tripfestival.exception.LandmarkFeeNotFoundException;
import com.tripfestival.repository.LandmarkFeeRepository;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.request.LandmarkFeeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkFeeService {
    private final LandmarkFeeRepository landmarkFeeRepository;

    private final LandmarkRepository landmarkRepository;

    public ResponseVo landmarkFeeInsert(LandmarkFeeProcessRequest req) {
        LandmarkFee landmarkFee = LandmarkFee.builder()
                .title(req.getTitle())
                .price(req.getPrice())
                .landmark(landmarkRepository.findById(req.getLandmarkId()).orElseThrow(() -> new LandmarkFeeNotFoundException()))
                .build();

        landmarkFeeRepository.save(landmarkFee);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkFeeDelete(Long landmarkFeeId) {
        LandmarkFee landmarkFee = landmarkFeeRepository.findById(landmarkFeeId)
                .orElseThrow(() -> new LandmarkFeeNotFoundException());

        landmarkFeeRepository.delete(landmarkFee);

        return new ResponseVo(Response.SUCCESS, null);
    }

}
