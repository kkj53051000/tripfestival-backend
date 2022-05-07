package com.tripfestival.service.landmark;

import com.tripfestival.domain.landmark.Landmark;
import com.tripfestival.domain.landmark.LandmarkHashTag;
import com.tripfestival.exception.landmark.LandmarkHashTagNotFoundException;
import com.tripfestival.exception.landmark.LandmarkNotFoundException;
import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import com.tripfestival.repository.landmark.LandmarkRepository;
import com.tripfestival.request.landmark.LandmarkHashTagProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.landmark.LandmarkHashTagAllListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkHashTagService {
    private final LandmarkHashTagRepository landmarkHashTagRepository;

    private final LandmarkRepository landmarkRepository;

    public ResponseVo landmarkHashTagInsert(LandmarkHashTagProcessRequest req) {
        Landmark landmark = landmarkRepository.findById(req.getLandmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException());

        LandmarkHashTag landmarkHashTag = LandmarkHashTag.builder()
                .name(req.getName())
                .landmark(landmark)
                .build();

        landmarkHashTagRepository.save(landmarkHashTag);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo landmarkHashTagDelete(Long landmarkHashTagId) {
        LandmarkHashTag landmarkHashTag = landmarkHashTagRepository.findById(landmarkHashTagId)
                .orElseThrow(() -> new LandmarkHashTagNotFoundException());

        landmarkHashTagRepository.delete(landmarkHashTag);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public LandmarkHashTagAllListVo landmarkHashTagAllListSelect() {
        List<LandmarkHashTag> landmarkHashTagList = landmarkHashTagRepository.findAll();

        if(landmarkHashTagList.size() == 0) {
            throw new LandmarkHashTagNotFoundException();
        }

        return new LandmarkHashTagAllListVo(landmarkHashTagList);
    }
}
