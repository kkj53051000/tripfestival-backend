package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkFee;
import com.tripfestival.dto.landmark.LandmarkFeeModifyDto;
import com.tripfestival.exception.landmark.LandmarkFeeNotFoundException;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.repository.landmark.LandmarkFeeRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.request.landmark.LandmarkFeeProcessRequest;
import com.tripfestival.vo.landmark.LandmarkFeeAllListVo;
import com.tripfestival.vo.landmark.LandmarkFeeListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public ResponseVo landmarkFeeAlert(LandmarkFeeModifyDto req) {
        LandmarkFee landmarkFee = landmarkFeeRepository.findById(req.getLandmarkFeeId())
                .orElseThrow(() -> new LandmarkFeeNotFoundException());

        if (req.getTitle() != null) {
            landmarkFee.setTitle(req.getTitle());
        }
        if (req.getPrice() != null) {
            landmarkFee.setPrice(req.getPrice());
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    public LandmarkFeeListVo landmarkFeeListSelect(Long landmarkId) {
        Landmark landmark = landmarkRepository.findById(landmarkId)
                .orElseThrow(() -> new LandmarkNotFoundException());

        List<LandmarkFee> landmarkFeeList = landmarkFeeRepository.findByLandmark(landmark)
                .orElseThrow(() -> new LandmarkFeeNotFoundException());

        return new LandmarkFeeListVo(landmarkFeeList);
    }

    public LandmarkFeeAllListVo landmarkFeeAllListSelect() {
        List<LandmarkFee> landmarkFeeList = landmarkFeeRepository.findAll();

        if(landmarkFeeList.size() == 0) {
            throw new LandmarkFeeNotFoundException();
        }

        return new LandmarkFeeAllListVo(landmarkFeeList);
    }

}
