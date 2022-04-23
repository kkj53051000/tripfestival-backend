package com.tripfestival.service;

import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.NatureHotspot;
import com.tripfestival.domain.NatureHotspotType;
import com.tripfestival.exception.LandmarkNotFoundException;
import com.tripfestival.exception.NatureHotspotNotFoundException;
import com.tripfestival.exception.NatureHotspotTypeNotFoundException;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.NatureHotspotRepository;
import com.tripfestival.repository.NatureHotspotTypeRepository;
import com.tripfestival.request.NatureHotspotProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NatureHotspotService {
    private final NatureHotspotRepository natureHotspotRepository;

    private final LandmarkRepository landmarkRepository;

    private final NatureHotspotTypeRepository natureHotspotTypeRepository;

    public ResponseVo natureHotspotInsert(NatureHotspotProcessRequest req) {
        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException());

        NatureHotspotType natureHotspotType = natureHotspotTypeRepository.findById(req.getNatureHotspotTypeId())
                .orElseThrow(() -> new NatureHotspotTypeNotFoundException());

        NatureHotspot natureHotspot = NatureHotspot.builder()
                .landmark(landmark)
                .natureHotspotType(natureHotspotType)
                .build();

        natureHotspotRepository.save(natureHotspot);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo natureHotspotDelete(Long natureHotspotId) {
        NatureHotspot natureHotspot = natureHotspotRepository.findById(natureHotspotId)
                .orElseThrow(() -> new NatureHotspotNotFoundException());

        natureHotspotRepository.delete(natureHotspot);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
