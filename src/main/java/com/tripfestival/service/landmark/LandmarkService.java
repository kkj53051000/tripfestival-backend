package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.*;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import com.tripfestival.dto.landmark.LandmarkListDto;
import com.tripfestival.dto.landmark.LandmarkModifyDto;
import com.tripfestival.dto.landmark.LandmarkProcessDto;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityNotFoundException;
import com.tripfestival.exception.world.WorldCountryCityRegionNotFoundException;
import com.tripfestival.repository.landmark.*;
import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import com.tripfestival.repository.world.WorldCountryCityRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    private final WorldCountryCityRepository worldCountryCityRepository;

    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;

    private final FileService fileService;

    private final LandmarkHashTagRepository landmarkHashTagRepository;

    private final LandmarkImgRepository landmarkImgRepository;

    private final LandmarkFeeRepository landmarkFeeRepository;

    private final LandmarkTimeRepository landmarkTimeRepository;

    private final LandmarkReviewRepository landmarkReviewRepository;

    @Transactional
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

    @Transactional
    public ResponseVo landmarkDelete(Long landmarkId) {
        Landmark landmark = landmarkRepository.findById(landmarkId)
                .orElseThrow(() -> new LandmarkNotFoundException());

        landmarkRepository.delete(landmark);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo landmarkClearDelete(Long landmarkId) {
        Landmark landmark = landmarkRepository.findById(landmarkId)
                .orElseThrow(() -> new LandmarkNotFoundException());

        // LandmarkHashTag Delete
        List<LandmarkHashTag> landmarkHashTagList = landmarkHashTagRepository.findByLandmark(landmark);

        for (LandmarkHashTag landmarkHashTag : landmarkHashTagList) {
            landmarkHashTagRepository.delete(landmarkHashTag);
        }

        // LandmarkImg Delete
        List<LandmarkImg> landmarkImgList = landmarkImgRepository.findByLandmark(landmark);

        for (LandmarkImg landmarkImg : landmarkImgList) {
            landmarkImgRepository.delete(landmarkImg);
        }

        // LandmarkFee Delete
        List<LandmarkFee> landmarkFeeList = landmarkFeeRepository.findByLandmark(landmark);

        for (LandmarkFee landmarkFee : landmarkFeeList) {
            landmarkFeeRepository.delete(landmarkFee);
        }

        // LandmarkTime Delete
        List<LandmarkTime> landmarkTimeList = landmarkTimeRepository.findByLandmark(landmark);

        for (LandmarkTime landmarkTime : landmarkTimeList) {
            landmarkTimeRepository.delete(landmarkTime);
        }

        // LandmarkReview Delete
        List<LandmarkReview> landmarkReviewList = landmarkReviewRepository.findByLandmark(landmark);

        for (LandmarkReview landmarkReview : landmarkReviewList) {
            landmarkReviewRepository.delete(landmarkReview);
        }

        landmarkRepository.delete(landmark);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public LandmarkListVo landmarkListSelect(LandmarkListDto req) {

        List<Landmark> landmarkList = null;

        if(req.getWorldCountryCityId() == 0) { // City 전체 Region 전체

            landmarkList = landmarkRepository.findAll();

        }else if(req.getWorldCountryCityRegionId() == 0) {  // City 선택 Region 전체
            WorldCountryCity worldCountryCity = worldCountryCityRepository.findById(req.getWorldCountryCityId())
                    .orElseThrow(() -> new WorldCountryCityNotFoundException());

            landmarkList = landmarkRepository.findByWorldCountryCityRegion_WorldCountryCity(worldCountryCity);
        }else {  // City, Region 둘다 선택
            WorldCountryCityRegion worldCountryCityRegion = worldCountryCityRegionRepository.findById(req.getWorldCountryCityRegionId())
                    .orElseThrow(() -> new WorldCountryCityRegionNotFoundException());

            landmarkList = landmarkRepository.findByWorldCountryCityRegion(worldCountryCityRegion);
        }


        // Landmark HashTag List
        List<List<LandmarkHashTag>> landmarkHashTagListVoList = new ArrayList<>();

        for (Landmark landmark : landmarkList) {
            List<LandmarkHashTag> landmarkHashTagList = landmarkHashTagRepository.findByLandmark(landmark);

            if (landmarkHashTagList.size() == 0) {
                landmarkHashTagListVoList.add(new ArrayList<LandmarkHashTag>());
            }else{
                System.out.println(landmarkHashTagList.size());

                List<LandmarkHashTag> items = landmarkHashTagList;
                landmarkHashTagListVoList.add(items);
            }
        }

        if(landmarkList.size() == 0) {
            throw new WorldCountryCityRegionNotFoundException();
        }

        return new LandmarkListVo(landmarkList, landmarkHashTagListVoList);
    }

    @Transactional(readOnly = true)
    public LandmarkVo landmarkSelect(Long landmarkId) {
        Landmark landmark = landmarkRepository.findById(landmarkId)
                .orElseThrow(() -> new LandmarkNotFoundException());

        List<LandmarkImg> landmarkImgList = landmarkImgRepository.findByLandmark(landmark);

        return new LandmarkVo(landmark, landmarkImgList);
    }

    @Transactional(readOnly = true)
    public LandmarkAllListVo landmarkAllListSelect() {
        List<Landmark> landmarkList = landmarkRepository.findAll();

        if (landmarkList.size() == 0) {
            throw new LandmarkNotFoundException();
        }

        return new LandmarkAllListVo(landmarkList);
    }

    @Transactional(readOnly = true)
    public LandmarkAllCountVo landmarkAllCountSelect() {
        Long landmarkAllCount = landmarkRepository.count();

        return new LandmarkAllCountVo(landmarkAllCount);
    }
}
