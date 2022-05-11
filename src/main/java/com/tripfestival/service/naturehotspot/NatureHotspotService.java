package com.tripfestival.service.naturehotspot;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.naturehotspot.NatureHotspotImgModifyDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotListDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotNatureHotspotTypeModifyDto;
import com.tripfestival.dto.naturehotspot.NatureHotspotProcessDto;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.naturehotspot.NatureHotspotNotFoundException;
import com.tripfestival.exception.naturehotspot.NatureHotspotTypeNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotRepository;
import com.tripfestival.repository.naturehotspot.NatureHotspotTypeRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotAllListVo;
import com.tripfestival.vo.naturehotspot.NatureHotspotListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NatureHotspotService {
    private final NatureHotspotRepository natureHotspotRepository;

    private final LandmarkRepository landmarkRepository;

    private final NatureHotspotTypeRepository natureHotspotTypeRepository;

    private final FileService fileService;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    public ResponseVo natureHotspotInsert(NatureHotspotProcessDto req) {

        String url = fileService.s3UploadProcess(req.getFile());

        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException());

        NatureHotspotType natureHotspotType = natureHotspotTypeRepository.findById(req.getNatureHotspotTypeId())
                .orElseThrow(() -> new NatureHotspotTypeNotFoundException());

        NatureHotspot natureHotspot = NatureHotspot.builder()
                .img(url)
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

    public ResponseVo natureHotspotImgAlert(NatureHotspotImgModifyDto req) {
        NatureHotspot natureHotspot = natureHotspotRepository.findById(req.getNatureHotspotId())
                .orElseThrow(() -> new NatureHotspotNotFoundException());

        String url = fileService.s3UploadProcess(req.getFile());

        natureHotspot.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public NatureHotspotListVo natureHotspotListSelect(NatureHotspotListDto req) {
        NatureHotspotType natureHotspotType = natureHotspotTypeRepository.findById(req.getNatureHotspotTypeId())
                .orElseThrow(() -> new NatureHotspotTypeNotFoundException());

        List<NatureHotspot> natureHotspotList = null;

        if(req.getWorldCountryCityId() == 0) { // 지역 전체

            natureHotspotList = natureHotspotRepository.findByNatureHotspotType(natureHotspotType);

        }else if(req.getWorldCountryCityRegionId() == 0) { // 지역 City만 필터
            WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException());

            natureHotspotList = natureHotspotRepository.findByNatureHotspotTypeAndLandmark_WorldCountryCityRegion_WorldCountryCity(natureHotspotType, worldCountryCity);
        }else { // 지역 City, Region 둘다 필터
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            natureHotspotList = natureHotspotRepository.findByNatureHotspotTypeAndLandmark_WorldCountryCityRegion(natureHotspotType, worldCountryCityRegion);
        }

        return new NatureHotspotListVo(natureHotspotList);
    }

    public NatureHotspotAllListVo natureHotspotAllListSelect() {
        List<NatureHotspot> natureHotspotList = natureHotspotRepository.findAll();

        if (natureHotspotList.size() == 0) {
            throw new NatureHotspotNotFoundException();
        }

        return new NatureHotspotAllListVo(natureHotspotList);
    }
}
