package com.tripfestival.service.naturehotspot;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.dto.naturehotspot.NatureHotspotNatureHotspotTypeModifyDto;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.naturehotspot.NatureHotspotNotFoundException;
import com.tripfestival.exception.naturehotspot.NatureHotspotTypeNotFoundException;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
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

    public ResponseVo natureHotspotNatureHotspotTypeAlert(NatureHotspotNatureHotspotTypeModifyDto req) {
        NatureHotspot natureHotspot = natureHotspotRepository.findById(req.getNatureHotspotId())
                .orElseThrow(() -> new NatureHotspotNotFoundException());

        NatureHotspotType natureHotspotType = natureHotspotTypeRepository.findById(req.getNatureHotspotTypeId())
                .orElseThrow(() -> new NatureHotspotTypeNotFoundException());

        natureHotspot.setNatureHotspotType(natureHotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
