package com.tripfestival.service.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.hotspot.HotspotHotspotTypeModifyDto;
import com.tripfestival.dto.hotspot.HotspotListDto;
import com.tripfestival.exception.hotspot.HotspotNotFoundException;
import com.tripfestival.exception.hotspot.HotspotTypeNotFoundException;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.hotspot.HotspotRepository;
import com.tripfestival.repository.hotspot.HotspotTypeRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.hotspot.HotspotProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotAllListVo;
import com.tripfestival.vo.hotspot.HotspotListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotspotService {
    private final HotspotRepository hotspotRepository;

    private final LandmarkRepository landmarkRepository;

    private final HotspotTypeRepository hotspotTypeRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

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

    public HotspotListVo hotspotListSelect(HotspotListDto req) {
        HotspotType hotspotType = hotspotTypeRepository.findById(req.getHotspotTypeId())
                .orElseThrow(() -> new HotspotTypeNotFoundException());

        List<Hotspot> hotspotList = null;

        if(req.getWorldCountryCityId() == 0) { // 지역 전체
            hotspotList = hotspotRepository.findByHotspotType(hotspotType);
        }else if(req.getWorldCountryCityRegionId() == 0) { // 지역 City만 필터
            WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException());

            hotspotList = hotspotRepository.findByHotspotTypeAndLandmark_WorldCountryCityRegion_WorldCountryCity(hotspotType, worldCountryCity);
        }else { // 지역 City, Region 둘다 필터
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            hotspotList = hotspotRepository.findByHotspotTypeAndLandmark_WorldCountryCityRegion(hotspotType, worldCountryCityRegion);
        }

        return new HotspotListVo(hotspotList);
    }

    public HotspotAllListVo hotspotAllListSelect() {
        List<Hotspot> hotspotList = hotspotRepository.findAll();

        if (hotspotList.size() == 0) {
            throw new HotspotNotFoundException();
        }

        return new HotspotAllListVo(hotspotList);
    }
}
