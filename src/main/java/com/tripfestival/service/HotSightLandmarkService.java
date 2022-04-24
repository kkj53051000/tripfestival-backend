package com.tripfestival.service;

import com.tripfestival.domain.HotSightLandmark;
import com.tripfestival.domain.HotSightTwo;
import com.tripfestival.domain.Landmark;
import com.tripfestival.exception.HotSightLandmarkNotFoundException;
import com.tripfestival.exception.HotSightTwoNotFoundException;
import com.tripfestival.repository.HotSightLandmarkRepository;
import com.tripfestival.repository.HotSightTwoRepository;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.request.HotSightLandmarkProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightLandmarkService {
    private final HotSightLandmarkRepository hotSightLandmarkRepository;

    private final LandmarkRepository landmarkRepository;

    private final HotSightTwoRepository hotSightTwoRepository;

    public ResponseVo hotSightLandmarkInsert(HotSightLandmarkProcessRequest req) {
        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new HotSightLandmarkNotFoundException());

        HotSightTwo hotSightTwo = hotSightTwoRepository.findById(req.getHotSightTwoId())
                .orElseThrow(() -> new HotSightTwoNotFoundException());

        HotSightLandmark hotSightLandmark = HotSightLandmark.builder()
                .description(req.getDescription())
                .landmark(landmark)
                .hotSightTwo(hotSightTwo)
                .build();

        hotSightLandmarkRepository.save(hotSightLandmark);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightLandmarkDelete(Long hotSightLandmarkId) {
        HotSightLandmark hotSightLandmark = hotSightLandmarkRepository.findById(hotSightLandmarkId)
                .orElseThrow(() -> new HotSightLandmarkNotFoundException());

        hotSightLandmarkRepository.delete(hotSightLandmark);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
