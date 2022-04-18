package com.tripfestival.service;

import com.tripfestival.domain.LandmarkImg;
import com.tripfestival.dto.LandmarkImgProcessDto;
import com.tripfestival.exception.LandmarkImgNotFoundException;
import com.tripfestival.repository.LandmarkImgRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkImgService {
    private final LandmarkImgRepository landmarkImgRepository;

    public ResponseVo landmarkImgInsert(LandmarkImgProcessDto req) {

        // 이미지 AWS 저장

        return new ResponseVo(Response.SUCCESS, null);
    }


    public ResponseVo landmarkImgDelete(Long landmarkImgId) {
        LandmarkImg landmarkImg = landmarkImgRepository.findById(landmarkImgId)
                .orElseThrow(() -> new LandmarkImgNotFoundException());

        landmarkImgRepository.delete(landmarkImg);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
