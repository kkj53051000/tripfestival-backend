package com.tripfestival.service.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.exception.hotspot.HotspotNotFoundException;
import com.tripfestival.exception.hotspot.HotspotTypeNotFoundException;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.repository.hotspot.HotspotRepository;
import com.tripfestival.repository.hotspot.HotspotTypeRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotspotService {
    private final HotspotRepository hotspotRepository;

    private final LandmarkRepository landmarkRepository;

    private final HotspotTypeRepository hotspotTypeRepository;

    public ResponseVo hotspotInsert(HotspotProcessRequest req) {
        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException());

        HotspotType hotspotType = hotspotTypeRepository.findById(req.getHotspotTypeId())
                .orElseThrow(() -> new HotspotTypeNotFoundException());

        Hotspot hotspot = Hotspot.builder()
                .landmark(landmark)
                .hotspotType(hotspotType)
                .build();

        hotspotRepository.save(hotspot);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotspotDelete(Long hotspotId) {
        Hotspot hotspot = hotspotRepository.findById(hotspotId)
                .orElseThrow(() -> new HotspotNotFoundException());

        hotspotRepository.delete(hotspot);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
