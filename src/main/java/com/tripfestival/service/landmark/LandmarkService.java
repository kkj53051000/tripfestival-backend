package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkModifyDto;
import com.tripfestival.dto.landmark.LandmarkProcessDto;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.request.landmark.LandmarkProcessRequest;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkAllListVo;
import com.tripfestival.vo.landmark.LandmarkListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final FileService fileService;

    public ResponseVo landmarkInsert(LandmarkProcessDto req) {

        String url = fileService.s3UploadProcess(req.getFile());

        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

        Landmark landmark = Landmark.builder()
                .name(req.getName())
                .img(url)
                .description(req.getDescription())
                .address(req.getAddress())
                .homepage(req.getHomepage())
                .worldCountryCityRegion(worldCountryCityRegion)
                .build();

        landmarkRepository.save(landmark);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkDelete(Long landmarkId) {
        Landmark landmark = landmarkRepository.findById(landmarkId).orElseThrow(() -> new LandmarkNotFoundException());

        landmarkRepository.delete(landmark);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkAlert(LandmarkModifyDto req) {
        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException());

        // find change
        if(req.getName() != null){
            landmark.setName(req.getName());
        }
        if(req.getDescription() != null){
            landmark.setDescription(req.getDescription());
        }
        if(req.getAddress() != null){
            landmark.setAddress(req.getAddress());
        }
        if(req.getHomepage() != null){
            landmark.setHomepage(req.getHomepage());
        }
        if(req.getWorldCountryCityRegionId() != null){
            landmark.setWorldCountryCityRegion(worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException()));
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    public LandmarkListVo landmarkListSelect(Long worldCountryCityRegionId) {
        WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(worldCountryCityRegionId)
                .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

        List<Landmark> landmarkList = landmarkRepository.findByWorldCountryCityRegion(worldCountryCityRegion);

        if(landmarkList.size() == 0) {
            throw new WorldCountryCityRegionNotFoundException();
        }

        return new LandmarkListVo(landmarkList);
    }

    public LandmarkAllListVo landmarkAllListSelect() {
        List<Landmark> landmarkList = landmarkRepository.findAll();

        if (landmarkList.size() == 0) {
            throw new LandmarkNotFoundException();
        }

        return new LandmarkAllListVo(landmarkList);
    }
}
