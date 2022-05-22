package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.LandmarkReview;
import com.tripfestival.domain.landmark.LandmarkReviewImg;
import com.tripfestival.dto.landmark.LandmarkReviewImgProcessDto;
import com.tripfestival.exception.landmark.LandmarkReviewImgNotFoundException;
import com.tripfestival.exception.landmark.LandmarkReviewNotFoundException;
import com.tripfestival.repository.landmark.LandmarkReviewImgRepository;
import com.tripfestival.repository.landmark.LandmarkReviewRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkReviewImgListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkReviewImgService {
    private final LandmarkReviewImgRepository landmarkReviewImgRepository;

    private final FileService fileService;

    private final LandmarkReviewRepository landmarkReviewRepository;

    @Transactional
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

    @Transactional
    public ResponseVo landmarkReviewImgDelete(Long landmarkReviewImgId) {
        LandmarkReviewImg landmarkReviewImg = landmarkReviewImgRepository.findById(landmarkReviewImgId)
                .orElseThrow(() -> new LandmarkReviewNotFoundException());

        landmarkReviewImgRepository.delete(landmarkReviewImg);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public LandmarkReviewImgListVo landmarkReviewImgListSelect(Long landmarkReviewId) {
        LandmarkReview landmarkReview = landmarkReviewRepository.findById(landmarkReviewId)
                .orElseThrow(() -> new LandmarkReviewNotFoundException());

        List<LandmarkReviewImg> landmarkReviewImgList = landmarkReviewImgRepository.findByLandmarkReview(landmarkReview)
                .orElseThrow(() -> new LandmarkReviewImgNotFoundException());

        return new LandmarkReviewImgListVo(landmarkReviewImgList);
    }
}
