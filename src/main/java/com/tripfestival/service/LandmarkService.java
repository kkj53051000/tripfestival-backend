package com.tripfestival.service;

import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.WorldCountry;
import com.tripfestival.exception.LandmarkNotFoundException;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.WorldCountryCityRepository;
import com.tripfestival.repository.WorldCountryRepository;
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

    private WorldCountryCityRepository worldCountryCityRepository;

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
}
