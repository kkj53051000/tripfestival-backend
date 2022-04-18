package com.tripfestival.service;

import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.LandmarkImg;
import com.tripfestival.domain.WorldCountry;
import com.tripfestival.domain.WorldCountryCity;
import com.tripfestival.dto.LandmarkImgProcessDto;
import com.tripfestival.exception.LandmarkImgNotFoundException;
import com.tripfestival.exception.LandmarkTimeNotFoundException;
import com.tripfestival.repository.LandmarkImgRepository;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkImgService {
    private final LandmarkImgRepository landmarkImgRepository;

    private final FileService fileService;

    private final LandmarkRepository landmarkRepository;

    public ResponseVo landmarkImgInsert(LandmarkImgProcessDto req) {

        // 이미지 AWS 저장
//        List<String> urls = fileService.s3UploadProcess(req.getFiles());
//
//        WorldCountry worldCountry = new WorldCountry("t", "t", "t", "t", "t");
//        WorldCountryCity worldCountryCity =new WorldCountryCity("t", "t", worldCountry);
//
//        Landmark landmarkt = Landmark.builder()
//                .name("test")
//                .description("test")
//                .address("test")
//                .homepage("test")
//                .worldCountryCity(worldCountryCity)
//                .build();
//
//        landmarkRepository.save(landmarkt);
//
//        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
//                .orElseThrow(() -> new LandmarkTimeNotFoundException());
//
//        for (String url : urls) {
//            LandmarkImg landmarkImg = new LandmarkImg(url, landmark);
//
//            landmarkImgRepository.save(landmarkImg);
//        }

        return new ResponseVo(Response.SUCCESS, null);
    }


    public ResponseVo landmarkImgDelete(Long landmarkImgId) {
        LandmarkImg landmarkImg = landmarkImgRepository.findById(landmarkImgId)
                .orElseThrow(() -> new LandmarkImgNotFoundException());

        landmarkImgRepository.delete(landmarkImg);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
