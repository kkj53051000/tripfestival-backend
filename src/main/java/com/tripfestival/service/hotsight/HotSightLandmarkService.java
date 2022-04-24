package com.tripfestival.service.hotsight;

import com.tripfestival.domain.hotsight.HotSightLandmark;
import com.tripfestival.domain.hotsight.HotSightTwo;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.dto.hotSight.HotSightLandmarkDescriptionModifyDto;
import com.tripfestival.dto.hotSight.HotSightLandmarkHotSightTwoModifyDto;
import com.tripfestival.exception.hotSight.HotSightLandmarkNotFoundException;
import com.tripfestival.exception.hotSight.HotSightTwoNotFoundException;
import com.tripfestival.repository.hotsight.HotSightLandmarkRepository;
import com.tripfestival.repository.hotsight.HotSightTwoRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.request.hotsight.HotSightLandmarkProcessRequest;
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

    public ResponseVo hotSightDescriptionAlert(HotSightLandmarkDescriptionModifyDto req) {
        HotSightLandmark hotSightLandmark = hotSightLandmarkRepository.findById(req.getHotSightLandmarkId())
                .orElseThrow(() -> new HotSightLandmarkNotFoundException());

        hotSightLandmark.setDescription(req.getDescription());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightLandmarkHotSightTwoAlert(HotSightLandmarkHotSightTwoModifyDto req) {
        HotSightLandmark hotSightLandmark = hotSightLandmarkRepository.findById(req.getHotSightLandmarkId())
                .orElseThrow(() -> new HotSightLandmarkNotFoundException());

        HotSightTwo hotSightTwo = hotSightTwoRepository.findById(req.getHotSightTwoId())
                .orElseThrow(() -> new HotSightTwoNotFoundException());

        hotSightLandmark.setHotSightTwo(hotSightTwo);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
