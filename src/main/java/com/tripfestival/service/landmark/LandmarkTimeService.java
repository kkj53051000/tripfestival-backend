package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.LandmarkTime;
import com.tripfestival.dto.landmark.LandmarkTimeModifyDto;
import com.tripfestival.exception.landmark.LandmarkTimeNotFoundException;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.landmark.LandmarkTimeRepository;
import com.tripfestival.request.landmark.LandmarkTimeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkTimeAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkTimeService {
    private final LandmarkTimeRepository landmarkTimeRepository;

    private final LandmarkRepository landmarkRepository;

    @Transactional
    public ResponseVo landmarkTimeInsert(LandmarkTimeProcessRequest req) {
        LandmarkTime landmarkTime = LandmarkTime.builder()
                .title(req.getTitle())
                .startTime(LocalTime.parse(req.getStartTime()))
                .endTime(LocalTime.parse(req.getEndTime()))
                .landmark(landmarkRepository.findById(req.getLandmarkId()).orElseThrow(() -> new LandmarkTimeNotFoundException()))
                .build();

        landmarkTimeRepository.save(landmarkTime);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo landmarkTimeDelete(Long landmarkTimeId) {
        LandmarkTime landmarkTime = landmarkTimeRepository.findById(landmarkTimeId)
                .orElseThrow(() -> new LandmarkTimeNotFoundException());

        landmarkTimeRepository.delete(landmarkTime);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo landmarkTimeAlert(LandmarkTimeModifyDto req) {
        LandmarkTime landmarkTime = landmarkTimeRepository.findById(req.getLandmarkTimeId())
                .orElseThrow(() -> new LandmarkTimeNotFoundException());

        if(req.getTitle() != null) {
            landmarkTime.setTitle(req.getTitle());
        }
        if(req.getStartTime() != null) {
            landmarkTime.setStartTime(LocalTime.parse(req.getStartTime()));
        }
        if(req.getEndTime() != null) {
            landmarkTime.setEndTime(LocalTime.parse(req.getEndTime()));
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public LandmarkTimeAllListVo landmarkTimeAllListSelect() {
        List<LandmarkTime> landmarkTimeList = landmarkTimeRepository.findAll();

        if(landmarkTimeList.size() == 0) {
            throw new LandmarkTimeNotFoundException();
        }

        return new LandmarkTimeAllListVo(landmarkTimeList);
    }
}
