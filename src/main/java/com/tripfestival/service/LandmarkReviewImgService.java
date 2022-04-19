package com.tripfestival.service;

import com.tripfestival.domain.Landmark;
import com.tripfestival.domain.LandmarkReview;
import com.tripfestival.domain.LandmarkReviewImg;
import com.tripfestival.dto.LandmarkReviewImgProcessDto;
import com.tripfestival.exception.LandmarkNotFoundException;
import com.tripfestival.exception.LandmarkReviewNotFoundException;
import com.tripfestival.repository.LandmarkRepository;
import com.tripfestival.repository.LandmarkReviewImgRepository;
import com.tripfestival.repository.LandmarkReviewRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkReviewImgService {
    private final LandmarkReviewImgRepository landmarkReviewImgRepository;

    private final FileService fileService;

    private final LandmarkReviewRepository landmarkReviewRepository;

    public ResponseVo landmarkReviewImgInsert(LandmarkReviewImgProcessDto req) {
        List<String> urls = fileService.s3UploadProcess(req.getFiles());

        LandmarkReview landmarkReview = landmarkReviewRepository.findById(req.getLandmarkReviewId())
                .orElseThrow(() -> new LandmarkReviewNotFoundException());

        for (String url : urls) {
            LandmarkReviewImg landmarkReviewImg = LandmarkReviewImg.builder()
                    .img(url)
                    .landmarkReview(landmarkReview)
                    .build();

            landmarkReviewImgRepository.save(landmarkReviewImg);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkReviewImgDelete(Long landmarkReviewImgId) {
        LandmarkReviewImg landmarkReviewImg = landmarkReviewImgRepository.findById(landmarkReviewImgId)
                .orElseThrow(() -> new LandmarkReviewNotFoundException());

        landmarkReviewImgRepository.delete(landmarkReviewImg);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
