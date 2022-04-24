package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkImg;
import com.tripfestival.dto.landmark.LandmarkImgProcessDto;
import com.tripfestival.exception.landmark.LandmarkImgNotFoundException;
import com.tripfestival.exception.landmark.LandmarkTimeNotFoundException;
import com.tripfestival.repository.landmark.LandmarkImgRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<String> urls = fileService.s3UploadProcess(req.getFiles());

        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkTimeNotFoundException());

        for (String url : urls) {
            LandmarkImg landmarkImg = new LandmarkImg(url, landmark);

            landmarkImgRepository.save(landmarkImg);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }


    public ResponseVo landmarkImgDelete(Long landmarkImgId) {
        LandmarkImg landmarkImg = landmarkImgRepository.findById(landmarkImgId)
                .orElseThrow(() -> new LandmarkImgNotFoundException());

        landmarkImgRepository.delete(landmarkImg);

        return new ResponseVo(Response.SUCCESS, null);
    }
}