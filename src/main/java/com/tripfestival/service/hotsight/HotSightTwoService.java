package com.tripfestival.service.hotsight;

import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.domain.hotsight.HotSightTwo;
import com.tripfestival.dto.hotSight.HotSightTwoImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightTwoNameModifyDto;
import com.tripfestival.dto.hotSight.HotSightTwoProcessDto;
import com.tripfestival.exception.hotSight.HotSightOneNotFoundException;
import com.tripfestival.exception.hotSight.HotSightTwoNotFoundException;
import com.tripfestival.repository.hotsight.HotSightOneRepository;
import com.tripfestival.repository.hotsight.HotSightTwoRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.hotsight.HotSightTwoListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightTwoService {
    private final HotSightTwoRepository hotSightTwoRepository;

    private final FileService fileService;

    private final HotSightOneRepository hotSightOneRepository;

    public ResponseVo hotSightTwoInsert(HotSightTwoProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        HotSightTwo hotSightTwo = HotSightTwo.builder()
                .name(req.getName())
                .img(url)
                .build();

        hotSightTwoRepository.save(hotSightTwo);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightTwoDelete(Long hotSightTwoId) {
        HotSightTwo hotSightTwo = hotSightTwoRepository.findById(hotSightTwoId)
                .orElseThrow(() -> new HotSightTwoNotFoundException());

        hotSightTwoRepository.delete(hotSightTwo);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hostSightTwoNameAlert(HotSightTwoNameModifyDto req) {
        HotSightTwo hotSightTwo = hotSightTwoRepository.findById(req.getHotSightTwoId())
                .orElseThrow(() -> new HotSightTwoNotFoundException());

        hotSightTwo.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotSightTwoImgAlert(HotSightTwoImgModifyDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        HotSightTwo hotSightTwo = hotSightTwoRepository.findById(req.getHotSightTwoId())
                .orElseThrow(() -> new HotSightTwoNotFoundException());

        hotSightTwo.setImg(url);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public HotSightTwoListVo hotSightTwoListSelect(Long hotSightOneId) {
        HotSightOne hotSightOne = hotSightOneRepository.findById(hotSightOneId)
                .orElseThrow(() -> new  HotSightOneNotFoundException());

        List<HotSightTwo> hotSightTwoList = hotSightTwoRepository.findByHotSightOne(hotSightOne)
                .orElseThrow(() -> new HotSightTwoNotFoundException());

        return new HotSightTwoListVo(hotSightTwoList);

    }
}
