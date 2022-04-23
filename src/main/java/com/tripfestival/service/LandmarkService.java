package com.tripfestival.service;

import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.dto.LandmarkModifyDto;
import com.tripfestival.exception.LandmarkNotFoundException;
import com.tripfestival.exception.WorldCountryCityNotFoundException;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.request.LandmarkModifyRequest;
import com.tripfestival.request.LandmarkProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    public ResponseVo landmarkInsert(LandmarkProcessRequest req) {

        Landmark landmark = Landmark.builder()
                .name(req.getName())
                .description(req.getDescription())
                .address(req.getAddress())
                .homepage(req.getHomepage())
                .worldCountryCity(worldCountryCityRepository.findById(req.getWorldCountryCityId()).orElseThrow(() -> new LandmarkNotFoundException()))
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

        // find change value
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
        if(req.getWorldCountryCityId() != null){
            landmark.setWorldCountryCity(worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException()));
        }

        return new ResponseVo(Response.SUCCESS, null);
    }
}
