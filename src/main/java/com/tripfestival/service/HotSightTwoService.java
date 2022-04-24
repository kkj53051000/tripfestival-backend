package com.tripfestival.service;

import com.tripfestival.domain.HotSightTwo;
import com.tripfestival.dto.HotSightTwoImgModifyDto;
import com.tripfestival.dto.HotSightTwoNameModifyDto;
import com.tripfestival.dto.HotSightTwoProcessDto;
import com.tripfestival.exception.HotSightTwoNotFoundException;
import com.tripfestival.repository.HotSightTwoRepository;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightTwoService {
    private final HotSightTwoRepository hotSightTwoRepository;

    private final FileService fileService;

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
}
